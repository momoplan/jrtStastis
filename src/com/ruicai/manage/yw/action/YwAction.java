package com.ruicai.manage.yw.action;

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
import com.ruicai.basis.entity.User;
import com.ruicai.basis.entity.Yw;
import com.ruicai.basis.yw.business.YwManage;
@Controller
public class YwAction{

	private static Log log = LogFactory.getLog(YwAction.class);
	
	private String viewPage;
	private String viewPage1;	
	
	@Autowired
	YwManage ywManage;

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

	@RequestMapping("/yewu/listyw.do")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response ,Yw command) {
		
		Yw model = new Yw();		
		
        List<Yw> ywlist = new ArrayList<Yw>();
		
		RollPage rollPage = new RollPage();

		try {
			ywlist = ywManage.findList(request, model, rollPage);
			request.setAttribute("list", ywlist);
			
		} catch (Exception e) {
			log.error("find Error", e);
		}
		
		return new ModelAndView(this.getViewPage());
	}
	
	@RequestMapping("/yewu/saveyw.do")
    public ModelAndView save(HttpServletRequest request,HttpServletResponse response ,Yw command) {

    	User operator = (User) request.getSession().getAttribute("user");

		try {
			Calendar cal = Calendar.getInstance();
			
			Yw model = new Yw();
			User user = new User();
			if(operator != null){
			  user.setId(operator.getId());
			}
			
			//model.setId(command.getId());
			model.setCode(command.getCode());
			model.setName(command.getName());
			model.setUser(user);
			model.setCjdate(cal.getTime());
			model.setStatus(1);
			model.setBz(command.getBz());

			ywManage.save(model);
		} catch (Exception e) {
			log.error("save Error", e);
		}
		
		try {
			response.sendRedirect("listyw.do");
		} catch (IOException e) {
			log.error("sendRedirect Error", e);
		}
		
		return null;
	}
    
	@RequestMapping("/yewu/delyw.do")
    public ModelAndView del(HttpServletRequest request,HttpServletResponse response ,Yw command) {
    	
    	String ids = request.getParameter("ids");
    	ids = ids.replace("'","");
    	ids = ids.substring(0,ids.lastIndexOf(','));
    	
    	try {
			ywManage.deleteJL(ids);
		} catch (Exception e) {
			log.error("del Error", e);
		}
    	
		try {
			response.sendRedirect("listyw.do");
		} catch (IOException e) {
			log.error("sendRedirect Error", e);
		}
		
		return null;
	}
    
	@RequestMapping("/yewu/updateyw.do")
    public ModelAndView update(HttpServletRequest request,HttpServletResponse response ,Yw command) {
    	
		try {
			Yw model = ywManage.findById(command.getId());
			//model.setId(command.getId());
			model.setCode(command.getCode());
			model.setName(command.getName());
			//model.setBduserid(1);
			//model.setCjdate(cal.getTime());
			model.setStatus(1);
			model.setBz(command.getBz());

			ywManage.update(model);
		} catch (Exception e) {
			log.error("update Error", e);
		}
		
		try {
			response.sendRedirect("listyw.do");
		} catch (IOException e) {
			log.error("sendRedirect Error", e);
		}
		
		return null;
	}
    
	@RequestMapping("/yewu/findyw.do")
    public ModelAndView find(HttpServletRequest request,HttpServletResponse response ,Yw command) {
    	
    	Integer id = TypeChange.objectToInt(request.getParameter("id"));

    	Yw model = null;
    	try {
    		model = ywManage.findById(id);    		
		} catch (Exception e) {
			log.error("find Error", e);
		}
		request.setAttribute("model", model);		
		
    	return new ModelAndView(this.getViewPage1());
	}

}
