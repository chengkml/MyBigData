package com.ck.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.ck.controller.base.Result;

@Aspect
@Component
public class WebExceptionAspect {

	private static final Log log = LogFactory.getLog(WebExceptionAspect.class);

	@Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
	private void restControllerPointcut() {
		
	}
	
	@Around("restControllerPointcut()")
	public Object around(ProceedingJoinPoint point) {
		Result result = new Result();
		try {
			log.info("around");
			result.setData(point.proceed());
		} catch (Throwable e) {
			log.error("服务调用异常！",e);
			result.setSuccess(false);
			result.setMsg(e.getMessage());
		}
		return result;
	}
}
