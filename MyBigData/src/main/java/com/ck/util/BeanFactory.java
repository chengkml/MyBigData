package com.ck.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
/**
 * @Title:获取Bean的工厂类
 * @author:Chengk
 * @Date:2018年11月15日
 * @Version:1.0
 */
public class BeanFactory implements ApplicationContextAware{
	/**
	 * Spring容器
	 */
	private static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println(applicationContext);
		context = applicationContext;
	}
	
	/**
	 * 获取bean
	 * @param clazz
	 * @return
	 */
	public static <T> T getBean(Class<T> clazz) {
		return context.getBean(clazz);
	}

}
