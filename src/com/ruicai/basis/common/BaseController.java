package com.ruicai.basis.common;

import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class BaseController extends SimpleFormController{
	
	/**
	 * @interpret 获取BEAN
	 * @param     name
	 * @return    Object
	 */
	public Object getBean(String name) {
		return WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()).getBean(name);
	}


}
