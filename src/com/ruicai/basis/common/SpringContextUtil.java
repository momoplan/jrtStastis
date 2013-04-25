package com.ruicai.basis.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringContextUtil.applicationContext = applicationContext; 

	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/** 
	 * 获取对象 
	 * @param name 
	 * @return Object 一个以所给名字注册的bean的实例 
	 * @throws BeansException 
	 */
	public static Object getBean(String name) throws BeansException {
		return applicationContext.getBean(name);
	}
	
	public static void main(String[] args) {
		//<bean id="SpringContextUtil" class="com.sufiworld.basis.common.SpringContextUtil" scope="singleton" lazy-init="false" />
		//<bean id="SpringContextUtil" class="com.encash.basis.common.SpringContextUtil" singleton="true" lazy-init="false" />
	}
}
