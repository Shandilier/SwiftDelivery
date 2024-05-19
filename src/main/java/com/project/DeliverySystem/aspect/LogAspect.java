package com.project.DeliverySystem.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LogAspect {
    Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution * com.project.DeliverySystem.controller.*.(..)")
    public void doOnMethodResponse(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        LOGGER.info(String.format("Controller called: %s", signature.getMethod().getName()));
    }

    @AfterReturning("doOnMethodResponse()")
    public void doOn(){

    }
}
