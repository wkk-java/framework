//package com.wk.common.exception;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.TypeReference;
//import feign.Response;
//import feign.Util;
//import feign.codec.ErrorDecoder;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.context.annotation.Configuration;
//
//@Log4j2
//@Configuration
//public class FeignErrorDecoder implements ErrorDecoder {
//
//    @Override
//    public Exception decode(String methodKey, Response response) {
////        try {
////            // 这里直接拿到我们抛出的异常信息
////            String message = Util.toString(response.body().asReader());
////            try {
////                JSONObject jsonObject = JSONObject.parseObject(message);
////                return new BusinessRuntimeException(ExceptionType.REMARK ,jsonObject.toString());
////            } catch (JSONException e) {
////                e.printStackTrace();
////            }
////
////        } catch (IOException ignored) {
////        }
////        return decode(methodKey, response);
//        try {
//            if (response.body() != null) {
//                String body = Util.toString(response.body().asReader());
//                log.error(body);
//                BusinessRuntimeException exceptionInfo = JSON.parseObject(body, new TypeReference<BusinessRuntimeException>() {
//                });
//                Class clazz = Class.forName(exceptionInfo.getMessage());
//                return (Exception) clazz.getDeclaredConstructor(String.class)
//                        .newInstance(exceptionInfo.getMessage());
//            }
//        } catch (Exception var4) {
//            log.error(var4.getMessage());
//            return new BusinessRuntimeException(ExceptionType.REMARK, var4.getMessage());
//        }
//        return new BusinessRuntimeException(ExceptionType.REMARK, "系统异常,请联系管理员");
//    }
//}