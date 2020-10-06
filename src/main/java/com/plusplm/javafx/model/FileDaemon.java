package com.plusplm.javafx.model;

import java.io.Serializable;

public class FileDaemon implements Serializable {

	private static final long serialVersionUID = 1291131027469631243L;
	
	/** 맥주소 */
	private String macIp;
	
	/** 네트워크드라이브 */
	private String networkDrive;
	
	/** 캐드프로그램 */
	private String cadProgram;
	
	/** 사용자아이디 */
	private String userId;
	
	/** 사용자아이디 */
	private String userPw;
	
	/** 등록DATE */
	private String regDate;
	
	/** 등록IP */
	private String regIp;

	public String getMacIp() {
		return macIp;
	}

	public void setMacIp(String macIp) {
		this.macIp = macIp;
	}

	public String getNetworkDrive() {
		return networkDrive;
	}

	public void setNetworkDrive(String networkDrive) {
		this.networkDrive = networkDrive;
	}
	
	public String getCadProgram() {
		return cadProgram;
	}

	public void setCadProgram(String cadProgram) {
		this.cadProgram = cadProgram;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getRegIp() {
		return regIp;
	}

	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}
}
