package com.plusplm.common.constants;

public class ErrorCode {
	public static final int RC_OK = 0;
	
	/*
	 * Common Error (1 ~ 100)
	 */
	public static final int NULL_ERROR = 1;
	public static final int IO_ERROR = 2;
	public static final int UNKNOWN_ERROR = 3;
	public static final int TYPE_CONVERSION_ERROR = 4;
	public static final int RESPONSE_IO_ERROR = 5;
	public static final int ENCODING_ERROR = 6;
	public static final int CRYPT_ERROR = 7;
	
	
	/*
	 * JSON FORMAT ERROR (101 ~ 200)
	 */
	public static final int INVALID_CUSTID = 101;
	public static final int INVALID_HEADER_FORMAT = 102;
	public static final int INVALID_BODY_FORMAT = 103;
	public static final int INVALID_PARAM_COUNT = 104;
	public static final int INVALID_PARAM_NAME = 105;
	public static final int INVALID_PARAM_VALUE = 106;
	
	/*
	 * AUTH ERROR(201 ~ 300)
	 */
	public static final int INVALID_USERID = 201;
	public static final int INVALID_PASSWD = 202;
	public static final int INVALID_USERINFO = 203;
	public static final int INVALID_ACCESS = 204;
	
	/*
	 * SQL (7000 ~ 8000)
	 */
	public static final int SQL_ERROR = 7000;
	public static final int SQL_KEY_VIOLATION_ERROR = 7001;
	public static final int SQL_UNIQUE_VIOLATION_ERROR = 23505;			// 유니크 제약 오류(중복된 키 값)
}
