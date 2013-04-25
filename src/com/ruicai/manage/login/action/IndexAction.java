package com.ruicai.manage.login.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ruicai.basis.common.TypeChange;
import com.ruicai.basis.entity.Role;
import com.ruicai.basis.entity.User;
import com.ruicai.basis.sys.business.RoleManage;
import com.ruicai.basis.sys.business.UserManage;

@Controller
public class IndexAction {

	private static Log log = LogFactory.getLog(IndexAction.class);

	private String viewPage;

	private String viewPage1;
	
	private String viewPage2;
	
	@Autowired
	UserManage userManage; 
	
	@Autowired
	RoleManage roleManage;
	
	public String getViewPage() {
		return viewPage;
	}

	public void setViewPage(String viewPage) {
		this.viewPage = viewPage;
	}

	public String getViewPage1() {
		return viewPage1;
	}

	public void setViewPage1(String viewPage1) {
		this.viewPage1 = viewPage1;
	}

	public String getViewPage2() {
		return viewPage2;
	}

	public void setViewPage2(String viewPage2) {
		this.viewPage2 = viewPage2;
	}

	// 用户登录
	@RequestMapping("/login.do")
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response, User command) {

		String code = TypeChange.objectToString(request.getParameter("code"));
		String randcode = TypeChange.objectToString(request.getSession().getAttribute("randCode"));

		boolean flag = false; //false
		if (!code.equals(randcode)) {
			flag = false;
			request.setAttribute("errormsg", "验证码错误");
		} else {
			User user = new User();
			user.setName(TypeChange.objectToString(request.getParameter("username")));
			user.setPass(TypeChange.objectToString(request.getParameter("password")));

			try {
				flag = userManage.findLoginInfo(user, request);
			} catch (Exception e) {
				log.error("index Error", e);
			}
			
			if(!flag){
				request.setAttribute("errormsg", "用户名或密码错误");
			}else{
				
				User operator = (User) request.getSession().getAttribute("user");
				Role role = null;
				if(operator!=null){
					role = roleManage.findById(operator.getRoleid());
				}
				
				request.getSession().setAttribute("role", role);
				request.getSession().setAttribute("user", operator);				
			}
		}

		if (flag) {
			try {
				response.sendRedirect("webmain.do");
			} catch (IOException e) {
				log.error("webmain.do not exist", e);
			}
			return null;
		} else {
			return new ModelAndView(this.getViewPage());
		}
	}
	
	@RequestMapping("/leftframe.do")
	public ModelAndView leftframe(HttpServletRequest request,
			HttpServletResponse response, User command) {
		
		/*
		RoleManage roleManage = (RoleManage) getBean("RoleManage");
		
		User operator = (User) request.getSession().getAttribute("user");
		Role role = null;
		if(operator!=null){
			role = roleManage.findById(operator.getRoleid());
		}
		
		request.setAttribute("role", role);
		*/
		request.setAttribute("user2", request.getSession().getAttribute("user"));
		return new ModelAndView(this.getViewPage2());
	}

	
}
