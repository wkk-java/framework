package com.wk.oauth.exception;

public class ErrorCode {

    private final int code;
    private final String message;
    private final String detailMessage;


    public static final String CLIENT_ID_IS_NULL_STR = "CLIENT_ID_IS_NULL";
    public static final String CLIENT_SECRET_IS_NULL_STR = "CLIENT_SECRET_IS_NULL";

    public static final String ORGANIZAITON_ID_IS_NULL_STR = "ORGANIZAITON_ID_IS_NULL";

    public static final ErrorCode DEVICE_ID_IS_NULL_STR = new ErrorCode("DEVICE_ID_IS_NULL_STR", "设备 ID 非法");
    public static final ErrorCode NOT_LOGIN_YET = new ErrorCode("NOT_LOGIN_YET", "没有用户已登录当前设备");

    public static final ErrorCode USER_SERVICE_CALL_FAILURE = new ErrorCode("USER_SERVICE_CALL_FAILURE", "无法调用远程用户系统，或调用失败");
    public static final ErrorCode ID_SERVICE_CALL_FAILURE = new ErrorCode("ID_SERVICE_CALL_FAILURE", "无法调用远程用ID_SERVICE，或调用失败");

    public static final ErrorCode INVALID_AUTHENTICATION_ERROR = new ErrorCode("INVALID_AUTHENTICATION", "用户证书校验失败");

    public static ErrorCode GENERIC_API_ERROR_CODE = new ErrorCode(0001, "GENERIC_API_ERROR_CODE", "generic API error message");
    public static ErrorCode GENERIC_UNAUTHORIZED_ERROR_CODE = new ErrorCode(0002, "GENERIC_UNAUTHORIZED_ERROR_CODE", "generic unauthorized error message");
    public static ErrorCode DATA_ACCESS_ERROR_CODE = new ErrorCode(0003, "DATA_ACCESS_ERROR", "database access error");

    public ErrorCode(int code, String message, String detailMessage) {
        this.code = code;
        this.message = message;
        this.detailMessage = detailMessage;
    }

    public ErrorCode(String message, String detailMessage) {
        this.code = 0;
        this.message = message;
        this.detailMessage = detailMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDetailMessage() {
        return detailMessage;
    }
}
