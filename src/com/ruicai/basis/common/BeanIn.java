package com.ruicai.basis.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class BeanIn {
	
	public static Object getBean(String name){
		ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:applicationContext-*.xml");
		//ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:com/zhcw/manage/properts/springxml/applicationContext-*.xml");
		Object obj=ctx.getBean(name);
		return obj;
	}

}
