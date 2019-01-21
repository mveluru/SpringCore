package WPEngine.WPEngineClient;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ApiClientTest extends Mockito{

@Test
public void testHttpClient(){
	// Given
	DefaultHttpClient httpClient = new DefaultHttpClient();
	HttpGet getRequest = new HttpGet("http://interview.wpengine.io/v1/accounts/" + 12345);
	getRequest.addHeader("accept", "application/json");
		   
	   // When
	   HttpResponse response = null;
	try {
		response = httpClient.execute(getRequest);
	} catch (ClientProtocolException e) {
		
		e.getMessage();
	} catch (IOException e) {
		
		e.getMessage();
	}
 
	   //Then
		assertEquals(HttpStatus.SC_OK ,response.getStatusLine().getStatusCode());
	  
}



}
