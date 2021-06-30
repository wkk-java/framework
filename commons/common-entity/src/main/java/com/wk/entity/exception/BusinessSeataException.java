package com.wk.entity.exception;


/**
 * @author: vince
 * create at: 2019/12/31 15:49
 * @description: 全局业务异常
 */
public class BusinessSeataException extends Exception{

    private String code;
    private String message;
    private Throwable throwable;

    public BusinessSeataException(ExceptionType exceptionType, String message) {
        this.code = exceptionType.getCode();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
