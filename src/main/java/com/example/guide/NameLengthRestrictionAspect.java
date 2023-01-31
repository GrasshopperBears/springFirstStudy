package com.example.guide;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
@Aspect
public class NameLengthRestrictionAspect {
    @Before("@annotation(NameLengthRestriction)")
    public void checkNameLength(JoinPoint jp) throws Throwable {
        String name = jp.getArgs()[0].toString();

        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        NameLengthRestriction nameLengthRestriction = method.getAnnotation(NameLengthRestriction.class);
        int limit = nameLengthRestriction.limit();

        if (name.length() > limit) {
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            response.sendError(HttpStatus.BAD_REQUEST.value(), String.format("Name should be shorter than %d", limit));
        }
    }
}
