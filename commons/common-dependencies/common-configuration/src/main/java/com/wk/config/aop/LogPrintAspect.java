package com.wk.config.aop;

import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: vince
 * create at: 2021/1/9 上午11:10
 * @description: 日志打印切面
 */
@Aspect
@Log4j2
public class LogPrintAspect {

    /**
     * 切面注册在方法上.
     */
//    @Pointcut("@annotation(com.wk.spring.annnotation.LogPrint)")
    @Pointcut("execution(* com.wk..*.controller..*(..)) || execution(* com.wk..*.service..*(..))")
    public void pointCutAtMethod() {

    }

    /**
     * 调用前.
     *
     * @param joinPoint 切面
     */
    @Before("pointCutAtMethod()")
    public void processBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request;
        if (attributes != null && (request = attributes.getRequest()) != null) {
            log.info("{} url:{}", request.getMethod(), request.getRequestURL());
            log.debug("客户端IP:{}", request.getRemoteAddr());
        }
        log.info("请求参数:{}", new Gson().toJson(joinPoint.getArgs()));
    }

    /**
     * 环绕切面.
     *
     * @param proceedingJoinPoint 调用切面
     * @return 返回值
     * @throws Throwable 异常
     */
    @Around("pointCutAtMethod()")
    public Object processAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        log.info("返回值:{}", result);
        log.info("耗时:{}", (System.currentTimeMillis() - startTime));
        return result;
    }

    /**
     * 调用后.
     *
     * @param result 返回值
     */
    @AfterReturning(value = "pointCutAtMethod()", returning = "result")
    public void processAfter(Object result) {

    }
}
