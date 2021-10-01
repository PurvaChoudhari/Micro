package com.demo.user.advice;


import org.aspectj.lang.ProceedingJoinPoint;


import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.fasterxml.jackson.databind.ObjectMapper;


@Aspect
@Component
public class LoggingAdvice {

	Logger log = LoggerFactory.getLogger(LoggingAdvice.class);
	
	@Pointcut(value="execution(* com.demo.user.*.*.*(..) )")
	public void myPointcut() {
		
	}
	
	@Around("myPointcut()")
	public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
		ObjectMapper mapper = new ObjectMapper();
		String methodName = pjp.getSignature().getName();
		String className = pjp.getTarget().getClass().toString();
		final StopWatch stopWatch = new StopWatch();
		Object[] array = pjp.getArgs();
		log.info("method invoked " + className + " : " + methodName + "()" + "arguments : "
				+ mapper.writeValueAsString(array));
		stopWatch.start();
		Object object = pjp.proceed();
		stopWatch.stop();
		log.info(className + " : " + methodName + "()" + "Response : "
				+ mapper.writeValueAsString(object));
		
		log.info("Execution time of " + className + "." + methodName + " :: " + stopWatch.getTotalTimeMillis() + " ms");
		return object;
	}

}


