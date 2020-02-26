package com.wts.framework.wechat.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Title: SpringContextUtil.java
 * @Package com.trt.framework.ixm.context
 * @Description: TODO
 * @author percyLee
 * @date 2018年1月11日 下午4:02:09
 */

@Component
public class SpringContextUtil implements ApplicationContextAware {
	
	private static ApplicationContext applicationContext = null;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (SpringContextUtil.applicationContext == null) {
			SpringContextUtil.applicationContext = applicationContext;
		}
	}
	
	// 获取applicationContext
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	// 通过name获取 Bean.
	public static <T> T getBean(String beanName) {
		return (T) getApplicationContext().getBean(beanName);
		
	}
	
	// 通过class获取Bean.
	public static <T> T getBean(Class<T> clazz) {
		return getApplicationContext().getBean(clazz);
	}
	
	// 通过name,以及Clazz返回指定的Bean
	public static <T> T getBean(String name, Class<T> clazz) {
		return getApplicationContext().getBean(name, clazz);
	}
	
}