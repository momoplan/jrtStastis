package com.ruicai.basis.common;

import javax.servlet.http.HttpServlet;

import org.springframework.web.context.support.WebApplicationContextUtils;

public class BaseHttpServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 31425473290765760L;

	/**
	 * @interpret 获取BEAN
	 * @param     name
	 * @return    Object
	 */
	public Object getBean(String name) {
		return WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()).getBean(name);
	}

}
