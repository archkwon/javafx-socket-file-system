package com.plusplm.javafx.model;

import java.io.Serializable;

public class ResponseCode implements Serializable{

	private static final long serialVersionUID = -8680912074953787277L;
	
	private String code;
	private String message;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
