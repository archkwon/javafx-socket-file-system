package com.plusplm.common.resource;

import java.io.Closeable;

import com.plusplm.DaemonRunner;

import javafx.application.Platform;

public class ResourceCloseHelper {

	public static void close(Closeable  ... resources) {
		for (Closeable resource : resources) {
			if (resource != null) {
				try {
					resource.close();
				} catch (Exception ignore) {
					ignore.getMessage();
				}
			}
		}
	}
	
	public static void daemonExit() {
		if(DaemonRunner.socket != null && DaemonRunner.socket.isConnected()){
			ResourceCloseHelper.closeScoketIO();
		}else{
			ResourceCloseHelper.closeJavaFX();
		}
	}
	
	public static void closeJavaFX() {
		try {
			DaemonRunner.isRun.close(); 
			if(DaemonRunner.isRun.isConnected()) {
				DaemonRunner.isRun.disconnect();  
	        }
			Platform.exit();
			System.exit(0);
		} catch (Exception ignore) {
			ignore.getMessage();
		}
	}
	
	public static void closeScoketIO() {
		try {
			DaemonRunner.socket.disconnect(); 
		} catch (Exception ignore) {
			ignore.getMessage();
		}
	}
}
