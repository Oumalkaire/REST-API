package com.atos.technicaltest.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.atos.technicaltest.dto.UserDto;
import com.atos.technicaltest.utils.Messages;

@Aspect
@Component
public class LoggingAspect {
	
	private Logger logger = LoggerFactory.getLogger(LoggingAspect.class.getName());
	
	@Before(value = "execution(* com.atos.technicaltest.service.UserService.*(..))")
	public void beforeAdvice(JoinPoint joinPoint) {
		logger.info(Messages.getMessageWithParam("logging.method.invoked", new String[]{
				joinPoint.getSignature().getDeclaringTypeName(),joinPoint.getSignature().getName()}));
	}
	
	@AfterReturning(pointcut = "execution(* com.atos.technicaltest.service.UserService.saveUser(..)) and args(userDto)")
	public void logAfterReturningsaveUser(UserDto userDto) {
		logger.info(Messages.getMessageWithParam("user.creation.successful", new String[]{userDto.userName()}));
	}
	
	@AfterReturning(pointcut = "execution(* com.atos.technicaltest.service.UserService.getUser(..)) and args(username)")
	public void logAfterReturningGetUser(String username) {
		logger.info(Messages.getMessageWithParam("user.retrieve.successful", new String[]{username}));
	}
}
