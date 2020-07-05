package com.wk.zuul.filter;

import com.netflix.zuul.context.RequestContext;
import com.wk.common.exception.BusinessRuntimeException;
import com.wk.common.exception.ExceptionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * @author: vince
 * create at: 2019/12/31 15:20
 * @description: 统一异常处理
 */
@Component
public class GlobalExceptionFilter extends SendErrorFilter {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionFilter.class);

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        // only forward to errorPath if it hasn't been forwarded to already
        boolean shouldFilter = ctx.getThrowable() != null && !ctx.getBoolean(SEND_ERROR_FILTER_RAN, false);
        logger.info("全局异常捕捉 shouldFilter..{0}", shouldFilter);
        return  shouldFilter;
    }

    @Override
    public Object run() {
        logger.info("全局异常捕捉 run..");
        RequestContext ctx = RequestContext.getCurrentContext();
        Throwable throwable = ctx.getThrowable();
        HttpServletRequest request = ctx.getRequest();
        if (throwable instanceof BusinessRuntimeException) {
            logger.error("出现业务错误了", throwable);
        } else {
            logger.error("出现非业务错误了", throwable);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("error");
        if (dispatcher != null) {
            ctx.set(SEND_ERROR_FILTER_RAN, true);
            if (!ctx.getResponse().isCommitted()) {
                ctx.setResponseStatusCode(HttpStatus.BAD_REQUEST.value());
                BusinessRuntimeException bre = new BusinessRuntimeException(ExceptionType.EXCEPTION_400, "error");
                bre.setThrowable(throwable);
                String response = ctx.getResponseBody();
                ctx.setResponseBody(bre.toString());
                try {
                    dispatcher.forward(request, ctx.getResponse());
                } catch (ServletException e) {
                    e.printStackTrace();
                    logger.error("转发错误", e);
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.error("转发错误", e);
                }
            }
        }
        return null;
    }
}
