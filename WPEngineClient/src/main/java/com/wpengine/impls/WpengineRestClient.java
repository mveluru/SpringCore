package com.wpengine.impls;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wpengine.model.Account;
import com.wpengine.model.AccountStatus;
import com.wpengine.propeties.PropertyReader;


public class WpengineRestClient {
	
	 static final Logger logger = Logger.getLogger(WpengineRestClient.class);
	
	ObjectMapper mapper = new ObjectMapper();
	String accounturl = null;
	PropertyReader properties = null;
	String filePath = null;
	String outfilePath = null;
	private final String OUTPUTFILENAME = "output.csv";

	enum Headers {
		Account_id, Account_Name, Fist_Name, Create_on, Status;
	};

	WpengineRestClient(String filepath, String outfilePath) {
		this.filePath = filepath;
		this.outfilePath = outfilePath;
		properties = new PropertyReader();
		accounturl = properties.getAccouturl();
	}
    /**
     * 
     * @param accounturl
     * @return
     */
	private AccountStatus callWPEngine(String accounturl) {

		AccountStatus accountstatus = null;

		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet(accounturl);
			getRequest.addHeader("accept", "application/json");

			HttpResponse response = httpClient.execute(getRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(response.getEntity().getContent())));

			String output;

			while ((output = br.readLine()) != null) {

				accountstatus = mapper.readValue(output, AccountStatus.class);
			}

			httpClient.getConnectionManager().shutdown();

		} catch (ClientProtocolException e) {

			logger.info(e.getMessage());
			logger.error(e.getMessage());

		} catch (IOException e) {

			logger.info(e.getMessage());
			logger.error(e.getMessage());
		}
		return accountstatus;
	}

    public void processFile() {

		CSVFormat format = CSVFormat.RFC4180.withHeader().withDelimiter(',');
		try {
			FileReader csvFile = new FileReader(filePath);

			CSVParser parser = new CSVParser(csvFile, format);

			List<Account> accounts = new ArrayList<Account>();
			for (CSVRecord record : parser) {
				Account account = new Account();
				account.setAccountId(Integer.parseInt(record.get(0)));
				account.setAccountName(record.get(1));
				account.setFirstName((record.get(2)));
				account.setDate((record.get(3)));
				account.setStatus(callWPEngine(accounturl + record.get(0))
						.getStatus());

				accounts.add(account);
			}

			parser.close();
			writetocsv(accounts);
			logger.info("File Process is completed and check out in the path "
							+ this.outfilePath + OUTPUTFILENAME);
			

		} catch (Exception e) {

		}
	}

	@SuppressWarnings("static-access")
	private void writetocsv(List<Account> accounts) {
		String outfile = this.outfilePath + OUTPUTFILENAME;
		CSVPrinter csvFilePrinter = null;

		try {
			FileWriter fileWriter = new FileWriter(outfile);
			 CSVFormat csvFileFormat =
			CSVFormat.EXCEL.withHeader(Headers.class).withDelimiter(',').withEscape(' ').withQuoteMode(QuoteMode.NONE);
			

			csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
			for (Account account : accounts) {
				csvFilePrinter.printRecords(account);
			}
			fileWriter.flush();
			fileWriter.close();
			csvFilePrinter.close();

		} catch (IOException e) {
			logger.info(e.getMessage());
			logger.error(e.getMessage());
			
		}

	}

	public static void main(String[] args) throws FileNotFoundException {
		if (args.length >= 2) {
			WpengineRestClient client = new WpengineRestClient(
					args[0].toString(), args[1].toString());
			client.processFile();
		} else {
			
			throw new FileNotFoundException("This application two arguments 1.Filepath-Inputfile 2.Filepath-OutputFile");
		}
	}
}
