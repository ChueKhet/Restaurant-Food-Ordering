package com.ternion.RFO.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerUtil {
	
	public static String getCurrentDate() {
		return new SimpleDateFormat("yyyyMMdd").format(new Date());
	}

}
