package com.example.aop.config;

import com.example.aop.annotation.IpLog;
import com.example.aop.exception.BaseException;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.stream.Collectors;


@Slf4j
@Aspect
@Component
public class RequestAspect {
    private String paramMapToString(Map<String, String[]> paramMap) {
        return paramMap.entrySet().stream()
                .map(entry -> String.format("%s -> (%s)",
                        entry.getKey(), Joiner.on(",").join(entry.getValue())))
                .collect(Collectors.joining(", "));
    }

    /*@Before("within(com.example.aop.controller..*)")
    public void methodParameterLogger(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        log.info("args: {}", args);
        log.info("method: {}", method);

        HttpServletRequest request = // 5
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        log.info("request: {}", request.getScheme());
    }*/

    @Before("within(com.example.aop.service..*)")
    public void methodParameterLogger2(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        log.info("args service: {}", args);
        log.info("method service: {}", method);
    }

    @Before("@annotation(ipLog)")
    public void methodParameterLogger3(IpLog ipLog) {
        HttpServletRequest request = // 5
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        log.info("userIp: {}", request.getRemoteAddr());
    }

    @Around("within(com.example.aop.controller..*)")
    public Object logging(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest request = // 5
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        Map<String, String[]> paramMap = request.getParameterMap();
        String params = "";
        if (paramMap.isEmpty() == false) {
            params = " [" + paramMapToString(paramMap) + "]";
        }

        long start = System.currentTimeMillis();
        long end = System.currentTimeMillis();
        log.info("Request: {} {}{} < {} ({}ms)", request.getMethod(), request.getRequestURI(),
                params, request.getRemoteHost(), end - start);
        log.info("params: {}", params);
        log.info("path: {}", request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE));

        Object proceed = pjp.proceed();

        log.info("after proceeding");

        return proceed;
    }

    @AfterReturning(value = "within(com.example.aop.service..*)", returning = "object")
    public void logging2(JoinPoint pjp, Object object) {
        log.info("pjp: {}",pjp.getSourceLocation().getWithinType());
        log.info("object: {}", object);
    }
}
