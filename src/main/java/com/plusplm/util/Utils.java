package com.plusplm.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import com.plusplm.common.global.Globals;

public class Utils {
	
	public static boolean isWindows() {
		return (Globals.OS_TYPE.indexOf("win") >= 0);
    }
	  
    public static boolean isMac() {
        return (Globals.OS_TYPE.indexOf("mac") >= 0);
    }
	  
    public static boolean isUnix() {
        return (Globals.OS_TYPE.indexOf("nix") >= 0 || Globals.OS_TYPE.indexOf("nux") >= 0 || Globals.OS_TYPE.indexOf("aix") > 0 );
    }
	  
    public static boolean isSolaris() {
        return (Globals.OS_TYPE.indexOf("sunos") >= 0);
    }
    
    public static String isMacUserIp(){
		String macIp = "";
		
		try{
			InetAddress addr = InetAddress.getLocalHost();
			NetworkInterface ni = NetworkInterface.getByInetAddress(addr);
		    byte[] mac = ni.getHardwareAddress();
			
		    for (int i = 0; i < mac.length; i++) {
		    	macIp += String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : "");
			}
		}catch(Exception e){
			e.getMessage();
		}
		return macIp;
	}
    
    public static String isLocalUserIp() {
    	String localIp = "";
		try {
			if (!WebUtils.isIPAddress(InetAddress.getLocalHost().getHostAddress())) {
				throw new RuntimeException("IP is needed. (" + InetAddress.getLocalHost().getHostAddress() + ")");
			}
			InetAddress local = InetAddress.getLocalHost();
			localIp = local.getHostAddress(); 
		} catch (UnknownHostException e) {
			e.getMessage();
		}
    	return localIp;
    }
    
    public static boolean isNetAvailable() throws Exception{
    	boolean status = false;
        try {
            final URL url = new URL("http://www.google.com");
            final URLConnection conn = url.openConnection();
            conn.connect();
            conn.getInputStream().close();
            status = true;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
        	e.getMessage();
        } 
        return status;
    }
    
    public static String isNowDate() throws Exception{

		String rtnStr = null;
		String pattern = "yyyy-MM-dd HH:mm:ss";
		
		try {
			SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern, Locale.KOREA);
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			rtnStr = sdfCurrent.format(ts.getTime());
		} catch (Exception e) {
			e.getMessage();
		}
		
		return rtnStr;
	}

    public static int isFileValidation(String filename) throws Exception{
    	int result = 0;
    	int pos = filename.lastIndexOf( "." );
		String ext = filename.substring( pos + 1 ).toUpperCase();
		String[] cadArray = {"VIZ", "ATT", "NWC", "RVM"};
		String[] normalArray = {"DOC", "DOCX", "XLS", "XLSX", "PPT", "PPTX", "PDF", "JPG", "JPEG", "PNG", "GIF", "BMP", "TXT", "CSV"
				,"PPTM", "POTM", "TIF", "AVI", "MP4", "MKV", "WMV", "MOV", "TS", "TP", "FLV", "WAV", "MP3"};
		        
		for(int i=0; i<normalArray.length; i++){
			if(ext.equals(normalArray[i])){
				result = 1;
			}
		}
		
		for(int j=0; j<cadArray.length; j++){
			if(ext.equals(cadArray[j])){
				result = -1;
			}
		}
		return result;
    }
    
    public static String isNull(Object param) throws Exception{
		String str = "";
		
		if (param == null) {
			return "";
		}
		
		if (param instanceof String) {
			str = (String) param;
		} else if (param instanceof String[]) {
			str = ((String[]) param)[0];
		} else if (param instanceof Date) {
			str = ((Date)param).toString();
		} else {
			str = String.valueOf(param);
		}
		
		if (str.equals("")) {
			return "";
		} else {
			return str.trim();
		}
	}
	
	public static String isStrNumNull(Object param) throws Exception{
		String str = "";
		
		if (param == null) {
			return "0";
		}
		
		if (param instanceof String) {
			str = (String) param;
		} else if (param instanceof String[]) {
			str = ((String[]) param)[0];
		} else if (param instanceof Date) {
			str = ((Date)param).toString();
		} else {
			str = String.valueOf(param);
		}
		
		if (str.equals("")) {
			return "0";
		} else {
			return str.trim();
		}
	}

	public static int isNumNull(Object param) throws Exception{
		String str = "";
		
		if (param == null) {
			return 0;
		}

		if (param instanceof String) {
			str = (String) param;
		} else if (param instanceof String[]) {
			str = ((String[]) param)[0];
		} else {
			str = String.valueOf(param);
		}
		
		if (str.equals("")) {
			return 0;
		} else {
			return Integer.parseInt(str);
		}
	}
	
	public static String Base64encode(String val) throws Exception {
		String rtnVal = val;
		byte[] targetBytes = rtnVal.getBytes();

		Encoder encoder = Base64.getEncoder();
		byte[] encodedBytes = encoder.encode(targetBytes);
		rtnVal = new String(encodedBytes);
		return rtnVal;
	}
	
	public static String Base64decode(String val) throws Exception {
		String rtnVal = val;
		byte[] targetBytes = rtnVal.getBytes();

		Decoder decoder = Base64.getDecoder();
		byte[] decodedBytes = decoder.decode(targetBytes);
		rtnVal = new String(decodedBytes);
		return rtnVal;
	}
}
