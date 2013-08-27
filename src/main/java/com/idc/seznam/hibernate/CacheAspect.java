package com.idc.seznam.hibernate;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.idc.seznam.HomeController;

//@Component
//@Aspect
public class CacheAspect {

	List<User> ulist = null;

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	public CacheAspect() {
		logger.info("POINTCUT_constructer");
	}

	
	@Before("execution(* com.idc.seznam.hibernate.*.*(..))")
	public void logAscpect(JoinPoint jp) { // pointcut signatura
		logger.info("POINTCUT");


	}

	@SuppressWarnings("unchecked")
	@Around("execution(* com.idc.seznam.hibernate.UserServiceImp.getUsers(..))")
	public Object cacheAscpect(ProceedingJoinPoint pjp) throws Throwable { // pointcut
																			// signatura
		logger.info("POINTCUT s joint pointem");

		Object retVal = null;
		if (ulist == null) {
			retVal = pjp.proceed();
			ulist = (List<User>) retVal;
		} else
			retVal = ulist;

		return retVal;
	}
	
	

	
	@Pointcut("execution(* com.idc.seznam.hibernate.UserServiceImp.getUsers(..))")
	public void myJoinPoint() {
	}

	
	@After("myJoinPoint()")
	public void after(JoinPoint jp) { // pointcut signatura
		logger.info("POINTCUT_after_get_users");
		logger.info(jp.getClass().toString());
	}
	
}
