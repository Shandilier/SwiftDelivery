package com.project.DeliverySystem.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AuthAspect {

    @Pointcut("@annotation(execution * com.project.DeliverySystem.annotation.Auth)")
    public void addAuth(){

    }

    @Before("adduth()")
    public void doAuthCheck(){

    }
}
