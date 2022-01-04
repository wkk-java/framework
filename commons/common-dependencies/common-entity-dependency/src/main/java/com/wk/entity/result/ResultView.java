package com.wk.entity.result;

import com.wk.entity.enums.ResultEnum;

import java.io.Serializable;

/**
 * 统一返回结果.
 * @param <T>
 */
public class ResultView<T> implements Serializable {
    private String code;
    private T data;
    private String msg;
    private String details;

    private ResultView(){
        this.code = ResultEnum.CODE_SUCCESS.getCode();
        this.msg = ResultEnum.CODE_SUCCESS.getMsg();
    }

    private ResultView(ResultEnum resultEnum){
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }

    public ResultView(String code, T data){
        this.code = code;
        this.data = data;
    }

    public static ResultView success(Object data){
        ResultView result = new ResultView();
        result.setCode(ResultEnum.CODE_SUCCESS.getCode());
        result.setMsg(ResultEnum.CODE_SUCCESS.getMsg());
        result.setData(data);
        return  result;
    }

    public ResultView<T> RestResult(ResultEnum resultEnum, T data, String ... details){
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
        this.data = data;
        this.details = (details != null && details.length >0) ? details[0]: "";
        return this;
    }

    public static ResultView success(ResultEnum resultEnum, Object data){
        ResultView result = new ResultView();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        result.setData(data);
        return  result;
    }

    public static ResultView error(String code, Object data){
        ResultView result = new ResultView();
        result.setCode(code);
        result.setData(data);
        return  result;
    }

    public static ResultView error(ResultEnum resultEnum, Object data){
        ResultView result = new ResultView();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        result.setData(data);
        return  result;
    }

    public static ResultView error(Object data){
        ResultView result = new ResultView();
        result.setCode(ResultEnum.CODE_FAILD.getCode());
        result.setMsg(ResultEnum.CODE_FAILD.getMsg());
        result.setData(data);
        return  result;
    }

    public String getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public String getDetails() {
        return details;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
