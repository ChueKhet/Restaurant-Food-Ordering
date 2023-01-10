package com.ternion.RFO.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerUtil {
	
	public static String getCurrentDate() {
		return new SimpleDateFormat("yyyyMMdd").format(new Date());
	}
	
	public static String generateUserId(int id) {
		String retId = "EM00000" + id;
		
		if(retId.length() > 8) {
			int lastIndex = 15 - retId.length();
			retId = retId.substring(0, lastIndex) + id;
		}
		
		return retId;
	}
}