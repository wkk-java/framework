package com.wk.zuul.exception;

import com.wk.common.exception.BusinessRuntimeException;
import com.wk.common.exception.ResultEnum;
import com.wk.common.exception.ResultView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;


/**
 * 文件异常处理
 */
@RestControllerAdvice
public class FileExceptionHandler {

    @Value("${zuul.upload.multipart.max-request-size}")
    private String maxRequestSize;

    private final Logger logger = LoggerFactory.getLogger(FileExceptionHandler.class);

    @ExceptionHandler(value = BusinessRuntimeException.class)
    public ResultView defaultErrorHandler(BusinessRuntimeException e) {
        e.printStackTrace();
        logger.error("出现错误了：", e);
        return ResultView.error(e.getMessage());
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResultView defaultErrorHandler(RuntimeException e) {

        if (e instanceof MaxUploadSizeExceededException) {
            return ResultView.error("上传的文件不能大于" + maxRequestSize + "！");
        }

        e.printStackTrace();
        createLogger(e);
        return ResultView.error(ResultEnum.CODE_666);
    }

    /**
     * 打印关键log信息
     *
     * @param e
     */
    private void createLogger(Exception e) {
        logger.info(e.getMessage());
        logger.info(e.getStackTrace()[0].toString());

        logger.error(e.getMessage());
        for (StackTraceElement stackTraceElement : e.getStackTrace()) {
            logger.error(stackTraceElement.toString());
        }
    }
}
