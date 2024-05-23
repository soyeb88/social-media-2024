package com.socialmedia.socialmedia2024v1.util;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@Aspect
public class LoggingAspect {
	public static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);
	
	@AfterThrowing(pointcut = "execution(* com.socialmedia.socialmedia2024v1.service.*.*(..))", throwing = "exception")
	public void logServiceException(Exception exception) {
		LOGGER.error(exception.getMessage(), exception);
	}
}
