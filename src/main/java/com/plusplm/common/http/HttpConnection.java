package com.plusplm.common.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.plusplm.common.constants.ErrorCode;
import com.plusplm.common.exception.ProcessingException;

public class HttpConnection {

	private final static String USER_AGENT = "Mozilla/5.0";
	
	public static String sendGet(String url) throws ProcessingException {
		int responseCode = 0;
		StringBuffer response = null;
		URL urlCon = null;
		HttpURLConnection conn = null;
		BufferedReader in = null;
		
		try	{
			urlCon = new URL(url);
			conn = (HttpURLConnection) urlCon.openConnection();
			
			conn.setRequestMethod("GET");
			conn.setRequestProperty("User-Agent", USER_AGENT);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			
			responseCode = conn.getResponseCode();
			
			if (HttpURLConnection.HTTP_OK == responseCode) {
				in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String inputLine;
				response = new StringBuffer();
				
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
			} else{
				in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			
		}catch(MalformedURLException e){
			e.getMessage();
			throw new ProcessingException(e.getMessage(), ErrorCode.RESPONSE_IO_ERROR);
		}catch(ProtocolException e){
			e.getMessage();
			throw new ProcessingException(e.getMessage(), ErrorCode.UNKNOWN_ERROR);
		}catch(UnsupportedEncodingException e){
			e.getMessage();	
			throw new ProcessingException(e.getMessage(), ErrorCode.ENCODING_ERROR);
		}catch(IOException e){
			e.getMessage();
			throw new ProcessingException(e.getMessage(), ErrorCode.IO_ERROR);
		}finally {
			if (conn instanceof HttpURLConnection) {
				((HttpURLConnection) conn).disconnect();
			}
			if (in != null) {
				try {
			        in.close();
			    }catch (IOException e) {
			        e.getMessage();
			    }
			}
		}
		return response.toString();
	}
	
	@SuppressWarnings("unused")
	public static JsonObject sendPost(String url, String json) throws ProcessingException {
		int responseCode = 0;
		StringBuffer response = null;
		URL urlCon = null;
		HttpURLConnection conn = null;
		BufferedReader in = null;
		OutputStream os = null;
		JsonObject obj = null;
		
		try	{
			urlCon = new URL(url);
			conn = (HttpURLConnection) urlCon.openConnection();
			
			conn.setRequestMethod("POST");
			conn.setRequestProperty("User-Agent", USER_AGENT);
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Content-type", "application/json");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			
			os = conn.getOutputStream();
			os.write(json.getBytes("utf-8"));
			os.flush();
			os.close();
			
			responseCode = conn.getResponseCode();
			
			if (HttpURLConnection.HTTP_OK == responseCode) {
				
				in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String inputLine;
				response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				Gson gson = new GsonBuilder().create();
				obj = new JsonParser().parse(response.toString()).getAsJsonObject();
			} else {
				in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			
		}catch(MalformedURLException e){
			e.getMessage();
			throw new ProcessingException(e.getMessage(), ErrorCode.RESPONSE_IO_ERROR);
		}catch(ProtocolException e){
			e.getMessage();
			throw new ProcessingException(e.getMessage(), ErrorCode.UNKNOWN_ERROR);
		}catch(UnsupportedEncodingException e){
			e.getMessage();	
			throw new ProcessingException(e.getMessage(), ErrorCode.ENCODING_ERROR);
		}catch(IOException e){
			e.getMessage();
			throw new ProcessingException(e.getMessage(), ErrorCode.IO_ERROR);
		}finally {
			if (conn instanceof HttpURLConnection) {
				((HttpURLConnection) conn).disconnect();
			}
			if (in != null) {
				try {
			        in.close();
			    }catch (IOException e) {
			        e.getMessage();
			    }
			}
			if (os != null) {
				try {
					os.close();
			    }catch (IOException e) {
			        e.getMessage();
			    }
			}
		}
		return obj;
	}
}
