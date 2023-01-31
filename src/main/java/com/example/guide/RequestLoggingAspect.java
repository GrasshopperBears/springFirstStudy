package com.example.guide;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RequestLoggingAspect {
    @Before("@annotation(RequestLogging)")
    public void requestLog(JoinPoint jp) {
        System.out.println(String.format("[request] name=%s", jp.getArgs()[0]));
    }
}
