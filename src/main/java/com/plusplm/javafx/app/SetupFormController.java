package com.plusplm.javafx.app;

import java.io.File;
import java.util.regex.Matcher;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.plusplm.common.constants.ErrorCode;
import com.plusplm.common.exception.ProcessingException;
import com.plusplm.common.global.Globals;
import com.plusplm.common.global.Messages;
import com.plusplm.common.http.HttpConnection;
import com.plusplm.common.resource.ResourceCloseHelper;
import com.plusplm.javafx.common.AlertHelper;
import com.plusplm.javafx.common.LoggerHelper;
import com.plusplm.javafx.model.FileDaemon;
import com.plusplm.javafx.model.ResponseCode;
import com.plusplm.util.Utils;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.Window;

public class SetupFormController {

	@FXML
	private TextField macIp;

	@FXML
	public ComboBox<String> networkDrive;

	@FXML
	private TextField cadProgram;

	@FXML
	public TextField userId;

	@FXML
	public PasswordField userPw;

	@FXML
	private Button btnSave;

	@FXML
	private Button btnCancel;

	@FXML
	private Button btnPopup;

	@FXML
	private TextArea console;

	private ObservableList<String> networkDriveList = FXCollections.observableArrayList();

	@FXML
	public void initialize() throws Exception {

		networkDriveList.add(new String("A:"));
		networkDriveList.add(new String("B:"));
		networkDriveList.add(new String("C:"));
		networkDriveList.add(new String("D:"));
		networkDriveList.add(new String("E:"));
		networkDriveList.add(new String("F:"));
		networkDriveList.add(new String("G:"));
		networkDriveList.add(new String("H:"));
		networkDriveList.add(new String("I:"));
		networkDriveList.add(new String("J:"));
		networkDriveList.add(new String("K:"));
		networkDriveList.add(new String("L:"));
		networkDriveList.add(new String("M:"));
		networkDriveList.add(new String("N:"));
		networkDriveList.add(new String("O:"));
		networkDriveList.add(new String("P:"));
		networkDriveList.add(new String("Q:"));
		networkDriveList.add(new String("R:"));
		networkDriveList.add(new String("S:"));
		networkDriveList.add(new String("T:"));
		networkDriveList.add(new String("U:"));
		networkDriveList.add(new String("V:"));
		networkDriveList.add(new String("W:"));
		networkDriveList.add(new String("X:"));
		networkDriveList.add(new String("Y:"));
		networkDriveList.add(new String("Z:"));

		networkDrive.setItems(networkDriveList);

		macIp.setEditable(false);
		cadProgram.setEditable(false);
		console.setEditable(false);
		networkDrive.setVisibleRowCount(8);

		try {
			String sendUrl = Globals.PLUSPLM_HTTP_HOST + Globals.DAEMON_SEARCH_URL + "?macIp=" + Utils.isMacUserIp();
			String result = HttpConnection.sendGet(sendUrl);

			Gson gson = new Gson();
			FileDaemon fileDaemon = gson.fromJson(result, FileDaemon.class);

			macIp.setText(Utils.isMacUserIp());
			networkDrive.getSelectionModel().select(fileDaemon.getNetworkDrive());
			cadProgram.setText(fileDaemon.getCadProgram());
			userId.setText(fileDaemon.getUserId());

			console.appendText(LoggerHelper.getLogger(Messages.MSG_INFORMATION_SAVE));

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
	private void handleComboBoxAction() throws ProcessingException {
		String selected = null;
		try {
			selected = networkDrive.getSelectionModel().getSelectedItem();
			console.appendText(LoggerHelper.getLogger(selected + Messages.MSG_DATA_SELECTED));
		} catch (Exception e) {
			throw new ProcessingException(e.getMessage(), ErrorCode.UNKNOWN_ERROR);
		}
	}

	@FXML
	private void handleSaveAction() throws ProcessingException, Exception {
		Window owner = btnSave.getScene().getWindow();

		if (macIp.getText().isEmpty()) {
			AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, Globals.DIALOG_INFO, Messages.MSG_MACIP_REQUIRED);
			console.appendText(LoggerHelper.getLogger(Messages.MSG_MACIP_REQUIRED));
			macIp.requestFocus();
			return;
		} else if (networkDrive.getSelectionModel().isEmpty()) {
			AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, Globals.DIALOG_INFO,
					Messages.MSG_NETWORK_DRIVE_REQUIRED);
			console.appendText(LoggerHelper.getLogger(Messages.MSG_NETWORK_DRIVE_REQUIRED));
			networkDrive.requestFocus();
			return;
		} else if (cadProgram.getText().isEmpty()) {
			AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, Globals.DIALOG_INFO,
					Messages.MSG_CADPROGRAM_REQUIRED);
			console.appendText(LoggerHelper.getLogger(Messages.MSG_CADPROGRAM_REQUIRED));
			cadProgram.requestFocus();
			return;
		} else if (userId.getText().isEmpty()) {
			AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, Globals.DIALOG_INFO,
					Messages.MSG_USERID_REQUIRED);
			console.appendText(LoggerHelper.getLogger(Messages.MSG_USERID_REQUIRED));
			userId.requestFocus();
			return;
		} else if (userPw.getText().isEmpty()) {
			AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, Globals.DIALOG_INFO,
					Messages.MSG_USERPW_REQUIRED);
			console.appendText(LoggerHelper.getLogger(Messages.MSG_USERPW_REQUIRED));
			userPw.requestFocus();
			return;
		} else {
			Stage primaryStage = new Stage();

			try {
				JSONObject json = new JSONObject();
				json.put("macIp", Utils.isMacUserIp());
				json.put("networkDrive", networkDrive.getSelectionModel().getSelectedItem());
				json.put("cadProgram", cadProgram.getText().replaceAll(Matcher.quoteReplacement(File.separator), "/"));
				json.put("userId", userId.getText());
				json.put("userPw", Utils.Base64encode(userPw.getText()));
				json.put("regDate", Utils.isNowDate());
				json.put("regIp", Utils.isLocalUserIp());

				JsonObject obj = HttpConnection.sendPost(Globals.PLUSPLM_HTTP_HOST + Globals.DAEMON_SAVE_URL,
						json.toString());

				Gson gson = new Gson();
				ResponseCode response = gson.fromJson(obj, ResponseCode.class);
				
				if (!"0000".equals(Utils.isNull(response.getCode()))) {
					AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, Globals.DIALOG_INFO,
							Utils.isNull(response.getMessage()));
					console.appendText(LoggerHelper.getLogger(Utils.isNull(response.getMessage())));
					return;
				} else {
					AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, Globals.DIALOG_INFO,
							Utils.isNull(response.getMessage()));
					console.appendText(LoggerHelper.getLogger(Utils.isNull(response.getMessage())));

					Parent root = FXMLLoader.load(getClass().getResource(Globals.ROOT_FXML));
					Scene scene = new Scene(root);
					scene.getStylesheets().add(getClass().getResource(Globals.DEFAULT_CSS).toExternalForm());
					primaryStage.setScene(scene);
					primaryStage.setTitle(Globals.DAEMON_TITLE);
					primaryStage.getIcons().add(new Image(Globals.ICON_IMAGE));
					primaryStage.setOnCloseRequest(e -> {
						ResourceCloseHelper.daemonExit();
					});
					primaryStage.show();

					Stage setup = (Stage) btnCancel.getScene().getWindow();
					setup.close();
				}
			} catch (Exception e) {
				System.out.println(Messages.MSG_API_ERROR + e);
			}
		}
	}

	@FXML
	private void handleCancelAction() throws ProcessingException {
		Stage primaryStage = new Stage();

		try {
			Parent root = FXMLLoader.load(getClass().getResource(Globals.ROOT_FXML));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource(Globals.DEFAULT_CSS).toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle(Globals.DAEMON_TITLE);
			primaryStage.getIcons().add(new Image(Globals.ICON_IMAGE));
			primaryStage.show();
			primaryStage.setOnCloseRequest(e -> {
				ResourceCloseHelper.daemonExit();
			});
			Stage setup = (Stage) btnCancel.getScene().getWindow();
			setup.close();

		} catch (Exception e) {
			throw new ProcessingException(e.getMessage(), ErrorCode.UNKNOWN_ERROR);
		}
	}

	@FXML
	private void handlePopupAction() throws ProcessingException {
		Stage stage = (Stage) btnPopup.getScene().getWindow();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Exe Files", "*.exe"));
		File selectedFile = fileChooser.showOpenDialog(stage);
		if (selectedFile != null) {
			cadProgram.setText(selectedFile.toString());
		}
	}
}
