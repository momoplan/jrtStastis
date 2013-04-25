package com.ruicai.manage.login.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ruicai.basis.entity.Menu;
import com.ruicai.basis.entity.User;
import com.ruicai.basis.sys.business.RoleManage;

@Controller
public class WebMainAction{
	
	private static Log log = LogFactory.getLog(WebMainAction.class);
	
	@Autowired
	RoleManage roleManage;

	private String viewPage;
	
	public String getViewPage() {
		return viewPage;
	}

	public void setViewPage(String viewPage) {
		this.viewPage = viewPage;
	}
	
	@RequestMapping("/webmain.do")
	public ModelAndView load(HttpServletRequest request, HttpServletResponse response ) {	
		
        User user = (User) request.getSession().getAttribute("user");
        
		List<Menu> list = null;
		try {
			list = roleManage.findUserMenu(user.getRoleid(), 0);
		} catch (Exception e) {
			log.error("Menu find Error", e);
		}
		request.setAttribute("list", list);
		
		return new ModelAndView(this.getViewPage());
	}	
}
