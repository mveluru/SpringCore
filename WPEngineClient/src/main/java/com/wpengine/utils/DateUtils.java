package com.wpengine.utils;

import java.text.SimpleDateFormat;

public class DateUtils {

	public String getDateMMDDYYYY(String dateString) {

		return new SimpleDateFormat("MM-dd-yyyy").format(dateString);

	}
	
	

}
