package com.plusplm;

import java.net.DatagramSocket;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.util.concurrent.TimeUnit;

import javax.management.monitor.MonitorSettingException;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.plusplm.common.constants.ErrorCode;
import com.plusplm.common.exception.ProcessingException;
import com.plusplm.common.global.Globals;
import com.plusplm.common.global.Messages;
import com.plusplm.common.http.HttpConnection;
import com.plusplm.common.resource.ResourceCloseHelper;
import com.plusplm.io.IOAcknowledge;
import com.plusplm.io.IOCallback;
import com.plusplm.io.SocketIO;
import com.plusplm.io.SocketIOException;
import com.plusplm.javafx.common.LoggerHelper;
import com.plusplm.javafx.model.FileDaemon;
import com.plusplm.util.Utils;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class DaemonRunner extends Application implements IOCallback {

	public static SocketIO socket;
	
	public static DatagramSocket isRun;
	
	@FXML
	private Label lbMacIp;
	
	@FXML
	private Label lbDrive;
	
	@FXML
	private Label lbUserId;
	
	@FXML
	private Label lbCadProgram;
	
	@FXML
	private Label lbUpdateDate;
	
	@FXML
	public Button btnSetup;
	
	@FXML
	private Button btnClose;
	
	@FXML
	private Button btnStatus;
	
	@FXML
	private TextArea console;
	
	public static String sessionUserId = "";
	
	public static void main(String[] args) throws ProcessingException {
		
		try {
			monitoring();
		} catch (MonitorSettingException e) {
			Platform.exit();
			System.exit(0);
		}

		try {
			socket = new SocketIO();
			socket.connect(Globals.PLUSPLM_SCOKET_HOST, new DaemonRunner());
			launch(args);
		} catch (MalformedURLException e) {
			throw new ProcessingException(e.getMessage(), ErrorCode.RESPONSE_IO_ERROR);
		} catch (Exception e) {
			throw new ProcessingException(e.getMessage(), ErrorCode.UNKNOWN_ERROR);
		}
	}
	
	public static void monitoring() throws MonitorSettingException {
        try {
            isRun = new DatagramSocket(1103);
        } catch (SocketException e) {
            throw new MonitorSettingException();
        }
    }
	
	@Override
	public void start(Stage primaryStage) throws ProcessingException {
		try {
			Parent root = FXMLLoader.load(getClass().getResource(Globals.ROOT_FXML));
			Scene scene = new Scene(root);
			
			scene.getStylesheets().add(getClass().getResource(Globals.DEFAULT_CSS).toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle(Globals.DAEMON_TITLE);
			primaryStage.setResizable(false);
			primaryStage.setOnCloseRequest(e -> {
				ResourceCloseHelper.daemonExit();
			});
			primaryStage.getIcons().add(new Image(Globals.ICON_IMAGE));
			primaryStage.show();
		} catch (Exception e) {
			throw new ProcessingException(e.getMessage(), ErrorCode.UNKNOWN_ERROR);
		}
	}
	
	@FXML
	private void initialize() throws Exception {
		String sendUrl = Globals.PLUSPLM_HTTP_HOST + Globals.DAEMON_SEARCH_URL + "?macIp=" + Utils.isMacUserIp();
		String result = null;
		
		try {
			result = HttpConnection.sendGet(sendUrl);
			
			Gson gson = new Gson();
			FileDaemon fileDaemon = gson.fromJson(result, FileDaemon.class);
			
			sessionUserId = fileDaemon.getUserId();
					
			lbMacIp.setText(fileDaemon.getMacIp());
			lbDrive.setText(fileDaemon.getNetworkDrive());
			lbUserId.setText(fileDaemon.getUserId());
			lbCadProgram.setText(fileDaemon.getCadProgram());
			lbUpdateDate.setText(fileDaemon.getRegDate());
			
			Tooltip tooltip = new Tooltip(fileDaemon.getCadProgram());
			lbCadProgram.setTooltip(tooltip);
			
			console.appendText(LoggerHelper.getLogger(Messages.MSG_INFORMATION_SETUP));
			console.setEditable(false);
			
			Platform.runLater(new Runnable() {
			    @Override
			    public void run() {
			    	console.requestFocus();
			    }
			});
		} catch (Exception e) {
			System.out.println(Messages.MSG_SOCKET_ERROR + e);
		}
	}
	
	@FXML
	public void handleStatusAction() throws ProcessingException, Exception {
		try {
			if(socket != null && socket.isConnected()){
				console.appendText(LoggerHelper.getLogger(Messages.MSG_CONNECTION_SUCCESS));
			}else{
				console.appendText(LoggerHelper.getLogger(Messages.MSG_CONNECTION_FAIL));
			}
		} catch (Exception e) {
			throw new ProcessingException(e.getMessage(), ErrorCode.UNKNOWN_ERROR);
		}
	}
	
	@FXML
	public void handleSetupAction() throws ProcessingException, Exception {
        Stage stage = new Stage();
        
		try {
			Parent root = FXMLLoader.load(getClass().getResource(Globals.SETUP_FXML));
			Scene scene = new Scene(root);
	        scene.getStylesheets().add(getClass().getResource(Globals.DEFAULT_CSS).toExternalForm());
	        stage.setScene(scene);
	        stage.setTitle(Globals.DAEMON_TITLE);
	        stage.setOnCloseRequest(e -> {
	        	ResourceCloseHelper.daemonExit();
	        });
	        stage.getIcons().add(new Image(Globals.ICON_IMAGE));
	        stage.show();
	        
	        Stage main = (Stage) btnSetup.getScene().getWindow();
	        main.close();
	        
		} catch (Exception e) {
			throw new ProcessingException(e.getMessage(), ErrorCode.UNKNOWN_ERROR);
		}
    }
		
	@FXML
	public void handleExitAction() throws Exception {
		ResourceCloseHelper.daemonExit();
	}
	
	@Override
	public void onMessage(org.json.JSONObject json, IOAcknowledge ack) {
		try {
			System.out.println(Messages.MSG_SOCKET_RESPONSE + json.toString(2));
		} catch (org.json.JSONException e) {
			ResourceCloseHelper.daemonExit();
		}
	}

	@Override
	public void onMessage(String data, IOAcknowledge ack) {
		System.out.println(Messages.MSG_SOCKET_RESPONSE + data);
	}

	@Override
	public void onError(SocketIOException socketIOException) {
		ResourceCloseHelper.daemonExit();
	}

	@Override
	public void onDisconnect() {
		System.out.println(Messages.MSG_SOCKET_DISCONNECTION);
	}

	@Override
	public void onConnect() {
		System.out.println(Messages.MSG_SOCKET_CONNECTION);
	}

	public void on(String event, IOAcknowledge ack, Object... args){
		String macip = "";
		String ndrive = "";
		String filepath = "";
		String filename = "";
		String cadprogram = "";
		String filetype = "";
		String paramuserId = "";
		
		try {
			if (Globals.COMMAND_FILEOPEN.equals(Utils.isNull(event))){
				JSONObject jsonObj = new JSONObject(Utils.isNull(args[0].toString()));
				
				if(!jsonObj.getString("macip").isEmpty() && !jsonObj.getString("ndrive").isEmpty()
						&& !jsonObj.getString("filepath").isEmpty() && !jsonObj.getString("filename").isEmpty() 
							&& !jsonObj.getString("filetype").isEmpty() && !jsonObj.getString("cadprogram").isEmpty()){
					
					macip = Utils.isNull(jsonObj.getString("macip"));
					ndrive = Utils.isNull(jsonObj.getString("ndrive"));
					filepath = Utils.isNull(jsonObj.getString("filepath"));
					filename = Utils.isNull(jsonObj.getString("filename"));
					cadprogram = Utils.isNull(jsonObj.getString("cadprogram"));
					filetype = Utils.isNull(jsonObj.getString("filetype"));
					paramuserId = Utils.isNull(jsonObj.getString("paramuserId"));
					
					if(sessionUserId.equals(paramuserId)) {
						if (macip.equals(Utils.isMacUserIp())) {
							if (Utils.isWindows()) {
								if("CAD".equals(filetype)) {
									cadWindowRuntime(filepath, cadprogram);
								}else{
									basicWindowRuntime(filepath);
								}
							}
						}
					}
				}
			}else if(Globals.COMMAND_EXITBYUSER.equals(Utils.isNull(event))){
				JSONObject jsonObj = new JSONObject(Utils.isNull(args[0].toString()));
				if(!jsonObj.getString("macip").isEmpty()){
					macip = Utils.isNull(jsonObj.getString("macip"));
					if (macip.equals(Utils.isMacUserIp())) {
						ResourceCloseHelper.daemonExit();
					}
				}
			}else if(Globals.COMMAND_EXITALL.equals(Utils.isNull(event))){
			    ResourceCloseHelper.daemonExit();
			}
		} catch (Exception e) {
			System.out.println(Messages.MSG_SOCKET_ERROR + e);
		}
	}

	public void basicWindowRuntime(String file) throws ProcessingException, Exception {
		Process process = null;
		String[] cmd = new String[] { "rundll32", "url.dll", "FileProtocolHandler", file };
		
		try {
			process = new ProcessBuilder(cmd).start();
			ResourceCloseHelper.close(process.getErrorStream());
			ResourceCloseHelper.close(process.getInputStream());
			ResourceCloseHelper.close(process.getOutputStream());
			
			if (!process.waitFor(2, TimeUnit.SECONDS)) { 
				process.destroy();
			} 
		} catch (Exception e) {
			process.destroy();
			throw new ProcessingException(e.getMessage(), ErrorCode.UNKNOWN_ERROR);
		} finally {
			//if (process != null && process.exitValue() != 0) {
			if (process != null) {	
				process.destroy();
			}
		}
	}
	
	public void cadWindowRuntime(String file, String cadprogram) throws ProcessingException, Exception {
		Process process = null;
        String[] cmd = {"cmd", "/c", "\"" + cadprogram + "\"" +  " " + file};
        
		try {
			process = new ProcessBuilder(cmd).start();
			ResourceCloseHelper.close(process.getErrorStream());
			ResourceCloseHelper.close(process.getInputStream());
			ResourceCloseHelper.close(process.getOutputStream());
			
			if (!process.waitFor(2, TimeUnit.SECONDS)) { 
				process.destroy();
			} 
		} catch (Exception e) {
			process.destroy();
			throw new ProcessingException(e.getMessage(), ErrorCode.UNKNOWN_ERROR);
		} finally {
			//if (process != null && process.exitValue() != 0) {
			if (process != null) {	
				process.destroy();
			}
		}
	}
}
