package com.wk.constants.enums;


/**
 * 结果枚举
 */
public enum ResultEnum {
    /**
     * 操作成功！
     */
    CODE_1("10000", "操作成功！"),
    /**
     * 操作失败！
     */
    CODE_2("10001", "操作失败！"),
    /**
     * 调用xxx服务失败！
     */
    CODE_3("10002", "调用{0}服务失败！"),
    /**
     * 验证码错误或已过期！
     */
    CODE_4("4", "验证码错误或已过期！"),
    /**
     * 用户名或者密码错误！
     */
    CODE_5("5", "用户名或者密码错误！"),
    /**
     * 账号已存在！
     */
    CODE_6("6", "账号已存在！"),
    /**
     * 该client_id不存在
     */
    CODE_7("7", "该client_id不存在！"),
    /**
     * token已失效或者已经注销登录过了！
     */
    CODE_8("8", "token已失效或者已经注销登录过了！"),

    /**
     * 文件不能为空！
     */
    CODE_9("9", "文件不能为空！"),
    /**
     * 文件格式不正确！
     */
    CODE_10("10", "文件格式不正确！"),
    /**
     * 文件不存在，下载失败！
     */
    CODE_11("11", "文件不存在，下载失败！"),


    /**
     * token无效！
     */
    CODE_401("401", "token无效！"),
    /**
     * 抱歉，您没有访问权限！
     */
    CODE_403("403", "抱歉，您没有访问权限！"),

    /**
     * 请求超时，请稍后再试！
     */
    CODE_504("504", "请求超时，请稍后再试！"),

    /**
     * 服务器神游中！
     */
    CODE_666("666", "服务器神游中！");


    /**
     * 状态码
     */
    private String code;

    /**
     * 消息
     */
    private String msg;

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
