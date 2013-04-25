package com.ruicai.basis.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ruicai.basis.common.BaseHttpServlet;
import com.ruicai.basis.common.ProUtil;
import com.ruicai.basis.entity.User;

/**
 * @author Administrator
 * @see 过滤所有URL地址判断用户是否已经登陆
 */
public class LandingFilter extends BaseHttpServlet implements Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(LandingFilter.class);	
	private ProUtil proutil = new ProUtil();
	private FilterConfig filterConfig;

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		
		User operator = (User) req.getSession().getAttribute("user");
		if (operator == null) {				
			res.sendRedirect("../jump.jsp");				
		} else {
			chain.doFilter(req, res);
		}
		
		/*
		HttpServletRequest req = (HttpServletRequest) request;

		HttpServletResponse res = (HttpServletResponse) response;
		
		res.setContentType(CONTENT_TYPE);
		
		chain.doFilter(req, res);
		
		User operator = (User) req.getSession().getAttribute("user");
		if (operator == null) {				
			res.sendRedirect("../jump.jsp");				
		} else {
			chain.doFilter(req, res);
		}
		*/
		
//		LotteryUser lotteryuser = (LotteryUser) req.getSession().getAttribute("LotteryUser");
//		//Session如果已经过期时
//		if(lotteryuser==null){
//			
//			if(req.getRequestURI().equals("/admin/admin_index.jsp")){
//				//返回用户主登陆页
//				RequestDispatcher dispatcher = req.getRequestDispatcher(proutil.getMessage("logindex.do"));
//				dispatcher.forward(req, res);
//
//				return;
//			}else{
//				//返回用户登陆页
//				RequestDispatcher dispatcher = req.getRequestDispatcher(proutil.getMessage("log.do"));
//				dispatcher.forward(req, res);
//
//				return;
//			}
//		}

	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}
	/**
	 * @interpret 获取BEAN
	 * @param name
	 * @return Object
	 */
	public Object getBean(String name) {
		return WebApplicationContextUtils.getRequiredWebApplicationContext(
				filterConfig.getServletContext()).getBean(name);
	}

	public FilterConfig getFilterConfig() {
		return filterConfig;
	}

	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

}
