package com.ruicai.manage.sys.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ruicai.basis.common.RollPage;
import com.ruicai.basis.common.TypeChange;
import com.ruicai.basis.entity.Role;
import com.ruicai.basis.entity.User;
import com.ruicai.basis.sys.business.RoleManage;
import com.ruicai.basis.sys.business.UserManage;

@Controller
public class UserAction{

	private static Log log = LogFactory.getLog(UserAction.class);
	@Autowired
	UserManage userManage;
	@Autowired
	RoleManage roleManage;
	
	private String viewPage;
	private String viewPage1;	
	private String viewPage2;	

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
	
	@RequestMapping("/sys/listuser.do")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response ,User command) {
		
		User model = new User();	
		
        List<User> userlist_ = new ArrayList<User>();
        List<User> userlist = new ArrayList<User>();
		
		RollPage rollPage = new RollPage();

		try {
			userlist_ = userManage.findList(request, model, rollPage);
			
			
		} catch (Exception e) {
			log.error("find Error", e);
		}
		
		
		for(User user :userlist_){
			Role role = roleManage.findById(user.getRoleid());	
			if(role!=null){
			    user.setRole(role);
			}else{
				role = new Role();
				role.setName("未知");
				user.setRole(role);
			}			
			userlist.add(user);
	        	
	     }	
		request.setAttribute("list", userlist);
		
		return new ModelAndView(this.getViewPage());
	}
	
	@RequestMapping("/sys/saveuser.do")
    public ModelAndView save(HttpServletRequest request,HttpServletResponse response ,User command) {
    	
    	if(!userManage.isSingleName(command.getName().trim())){    	
    		request.setAttribute("model", command);
    		request.setAttribute("msg", "用户名不能重复");
    		return newuser(request,response,command);
    	}

		try {
			Calendar cal = Calendar.getInstance();
			
			User model = new User();
			
			model.setRoleid(command.getRoleid());
			model.setName(command.getName());
			model.setRealname(command.getRealname());
			model.setTel(command.getTel());
			model.setRegdate(cal.getTime());
			model.setStatus(1);
			model.setBz(command.getBz());
			model.setPass(command.getPass());
					
			userManage.save(model);
		} catch (Exception e) {
			log.error("save Error", e);
		}
		
		try {
			
			response.sendRedirect("listuser.do");
		} catch (IOException e) {
			log.error("sendRedirect Error", e);
		}
		
		return null;
	}
	
	@RequestMapping("/sys/deluser.do")
    public ModelAndView del(HttpServletRequest request,HttpServletResponse response ,User command) {
    	
    	String ids = request.getParameter("ids");
    	ids = ids.replace("'","");
    	ids = ids.substring(0,ids.lastIndexOf(','));
    	
    	try {
    		userManage.deleteJL(ids);
		} catch (Exception e) {
			log.error("del Error", e);
		}
    	
		try {
			response.sendRedirect("listuser.do");
		} catch (IOException e) {
			log.error("sendRedirect Error", e);
		}
		
		return null;
	}
	
	@RequestMapping("/sys/updateuser.do")
    public ModelAndView update(HttpServletRequest request,HttpServletResponse response ,User command) {

		try {
			User model = userManage.findById(command.getId());
            
			//System.out.println(command.getRoleid());
			model.setRoleid(command.getRoleid());
			//model.setName(command.getName());
			model.setRealname(command.getRealname());
			model.setTel(command.getTel());
			model.setBz(command.getBz());
			if(!command.getPass().equals("")){
			   model.setPass(command.getPass());
			}
			
			userManage.update(model);
		} catch (Exception e) {
			log.error("update Error", e);
		}
		
		try {
			response.sendRedirect("listuser.do");
		} catch (IOException e) {
			log.error("sendRedirect Error", e);
		}
		
		return null;
	}
	
	@RequestMapping("/sys/finduser.do")
    public ModelAndView find(HttpServletRequest request,HttpServletResponse response ,User command) {

        List<Role> rolellist = new ArrayList<Role>();

		try {
			rolellist = roleManage.findAll();
			request.setAttribute("list", rolellist);
			
		} catch (Exception e) {
			log.error("newuser Error", e);
		}
    	Integer id = TypeChange.objectToInt(request.getParameter("id"));

    	User model = null;
    	try {
    		model = userManage.findById(id);    		
		} catch (Exception e) {
			log.error("find Error", e);
		}
		request.setAttribute("model", model);		
		
    	return new ModelAndView(this.getViewPage1());
	}
	
	@RequestMapping("/sys/newuser.do")
    public ModelAndView newuser(HttpServletRequest request,HttpServletResponse response ,User command) {

        List<Role> rolellist = new ArrayList<Role>();

		try {
			rolellist = roleManage.findAll();
			request.setAttribute("list", rolellist);
			
		} catch (Exception e) {
			log.error("newuser Error", e);
		}   
		
    	return new ModelAndView(this.getViewPage2());
	}

}
