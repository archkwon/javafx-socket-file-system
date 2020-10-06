
package com.plusplm.common.global;

public class Globals {

	public static final String OS_TYPE = System.getProperty("os.name").toLowerCase();
	public static final String TOKEN_SPLIT = "@|";
	public static final String PLUSPLM_SCOKET_HOST = "http://localhost:3001/";
	
	public static final String PLUSPLM_HTTP_HOST = "http://localhost:8080";
	public static final String DAEMON_SAVE_URL = "/api/v1/fileDaemon";
	public static final String DAEMON_SEARCH_URL = "/api/v1/detail/fileDaemon";
	public static final String DAEMON_TITLE = "file daemon v1.0";
	
	public static final String COMMAND_FILEOPEN = "fileopen";
	public static final String COMMAND_EXITALL = "exitall";
	public static final String COMMAND_EXITBYUSER = "exitbyuser";
	
	public static final String ROOT_FXML = "/application/view/root.fxml";
	public static final String SETUP_FXML = "/application/view/setup.fxml";
	public static final String DEFAULT_CSS = "/application/css/bootstrap.css";
	public static final String ICON_IMAGE = "/application/image/daemon_icon.png";
	
	public static final String DIALOG_INFO = "Information Dialog";
}
