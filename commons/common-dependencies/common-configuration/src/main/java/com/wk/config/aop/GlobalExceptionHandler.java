package com.wk.config.aop;

import com.wk.entity.enums.ResultEnum;
import com.wk.entity.exception.BusinessRuntimeException;
import com.wk.entity.result.ResultView;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 全局异常处理
 */
@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BusinessRuntimeException.class)
    public ResultView defaultErrorHandler(BusinessRuntimeException e) {
        log.error("出现错误了：", e);
        return ResultView.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResultView defaultErrorHandler(RuntimeException e) {
//        e.printStackTrace();
        createLogger(e);
        return ResultView.error(ResultEnum.CODE_666);
    }

    /**
     * 打印关键log信息
     *
     * @param e 异常
     */
    private void createLogger(Exception e) {
        log.error(e.getMessage());
        for (StackTraceElement stackTraceElement : e.getStackTrace()) {
            log.error(stackTraceElement.toString());
        }
    }
}
