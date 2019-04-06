package com.com.com.webaaa.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    @Before("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void startLog(JoinPoint jp) {
        System.out.println(jp.getSignature());
    }

    @After("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void endLog(JoinPoint jp) {
        System.out.println(jp.getSignature());
    }
}
