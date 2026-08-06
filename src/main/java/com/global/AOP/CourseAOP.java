package com.global.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class CourseAOP {
	Logger log = LoggerFactory.getLogger(CourseAOP.class);
	
	
	@Around("annotation (com.global.annotation.LogExecution)")
	public void methodExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
		Long start = System.currentTimeMillis();
		Object execute = pjp.proceed();
		Long end = System.currentTimeMillis();
		Long totalTimeExecution=end-start;
		log.info("total time execution for method => "+ pjp.getSignature().getName() + " <= is " + totalTimeExecution);
		
	}

}
