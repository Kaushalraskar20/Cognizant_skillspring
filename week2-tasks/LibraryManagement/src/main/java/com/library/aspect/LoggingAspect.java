package com.library.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * LoggingAspect - AOP Aspect for cross-cutting logging concern.
 *
 * Exercise 3: Tracks method execution times using @Around advice.
 * Exercise 8: Adds @Before and @After advice to demonstrate all advice types.
 *
 * @Aspect marks this class as an Aspect (a modular unit of cross-cutting concern).
 * The bean is registered in applicationContext.xml and activated by <aop:aspectj-autoproxy/>.
 *
 * Pointcut expression "execution(* com.library.service.*.*(..))" means:
 *   - execution: intercept method execution
 *   - *           : any return type
 *   - com.library.service.*  : any class in the service package
 *   - .*(..)     : any method with any arguments
 */
@Aspect
public class LoggingAspect {

    /**
     * Exercise 8: @Before advice - runs BEFORE the target method executes.
     */
    @Before("execution(* com.library.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("[AOP - Before] Entering method: "
                + joinPoint.getSignature().getName());
    }

    /**
     * Exercise 8: @After advice - runs AFTER the target method (whether or not
     * it threw an exception).
     */
    @After("execution(* com.library.service.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("[AOP - After]  Exiting method: "
                + joinPoint.getSignature().getName());
    }

    /**
     * Exercise 3: @Around advice - wraps the method to measure execution time.
     * ProceedingJoinPoint.proceed() actually calls the original method.
     */
    @Around("execution(* com.library.service.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed(); // call the actual method

        long timeTaken = System.currentTimeMillis() - startTime;
        System.out.println("[AOP - Around] Method " + joinPoint.getSignature().getName()
                + " executed in " + timeTaken + " ms");

        return result;
    }
}
