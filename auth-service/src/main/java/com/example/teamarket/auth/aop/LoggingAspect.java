package com.example.teamarket.auth.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("within(com.example.teamarket.auth.services.impl..*)")
    public void applicationPackagePointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    @Before("applicationPackagePointcut()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Starting execution of: {}", joinPoint.getSignature().getName());
    }

    @After("applicationPackagePointcut()")
    public void logAfter(JoinPoint joinPoint) {
        log.info("Completed execution of: {}", joinPoint.getSignature().getName());
    }

    @Around("applicationPackagePointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long executionTime = System.currentTimeMillis() - start;
            log.info("{} executed in {} ms", joinPoint.getSignature().getName(), executionTime);
            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument: {} in {}.{}()",
                    java.util.Arrays.toString(joinPoint.getArgs()),
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName());
            throw e;
        }
    }
}
