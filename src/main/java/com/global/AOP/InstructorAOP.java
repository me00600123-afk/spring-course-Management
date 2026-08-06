package com.global.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class InstructorAOP {
	Logger log = LoggerFactory.getLogger(InstructorAOP.class);

	@Around("@annotation (com.global.annotation.LogExecution)")
	public Object methodExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
		Long start = System.currentTimeMillis();
		Object execute = pjp.proceed();
		Long end = System.currentTimeMillis();
		Long totalTimeExecution=end-start;
		log.info("total time execution for method => "+ pjp.getSignature().getName() + " <= is " + totalTimeExecution);
		
		return execute;
		

}
}
