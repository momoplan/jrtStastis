package com.ruicai.manage.login.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ruicai.basis.common.TypeChange;
import com.ruicai.basis.entity.User;
import com.ruicai.basis.sys.business.UserManage;

@Controller
@RequestMapping("/pass")
public class PassAction {
	
	private static Log log = LogFactory.getLog(PassAction.class);

	private String viewPage;
	
	@Autowired
	UserManage userManage;
	
	public String getViewPage() {
		return viewPage;
	}

	public void setViewPage(String viewPage) {
		this.viewPage = viewPage;
	}
	
	@RequestMapping("/updatepass.do")
	public ModelAndView update(HttpServletRequest request,
			HttpServletResponse response ,User command) {
		String password = TypeChange.objectToString(request.getParameter("password"));
		String password1 = TypeChange.objectToString(request.getParameter("password1"));
		User user = (User) request.getSession().getAttribute("user");
		//System.out.println(user.getPass()+"  "+password);
		if(user.getPass().equals(password)){
			user.setPass(password1);
			try {
			    userManage.update(user);
			} catch (Exception e) {
				log.error("update Error", e);
			}
			request.setAttribute("msg", "alert('保存成功');window.location.href = 'userinfo.jsp';");
		}else{
			request.setAttribute("msg", "alert('原密码输入错误');window.location.href = 'userinfo.jsp';");
		}		
		
		return new ModelAndView(this.getViewPage());
	}
	
	@RequestMapping("/findpass.do")
	public ModelAndView find(HttpServletRequest request, HttpServletResponse response) {	
		
		return new ModelAndView(this.getViewPage());
	}
		
}
