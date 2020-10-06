package com.plusplm.javafx.common;

import com.plusplm.util.Utils;

public class LoggerHelper {

	 public static String getLogger(String message) throws Exception {
		 String result = "[" + Utils.isNowDate() + "] : " + message + "\n";
		 return result;
	 }
}
