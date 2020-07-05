package com.wk.common.exception;

/**
 * 自定义异常类型.
 */
public enum ExceptionType {

    REMARK("10000","{0}"),
    EXCEPTION_400("400","{0}");

    private String code;
    private String text;

    public String getCode() {
        return code;
    }
    public String getText() {
        return text;
    }

    ExceptionType(String code, String text) {
        this.code = code;
        this.text = text;
    }
}
