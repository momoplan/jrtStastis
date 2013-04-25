package com.ruicai.manage.sys.action;

import java.io.IOException;
import java.util.ArrayList;
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
import com.ruicai.basis.entity.Menu;
import com.ruicai.basis.sys.business.MenuManage;

@Controller
public class MenuAction{

	private static Log log = LogFactory.getLog(MenuAction.class);
	
	@Autowired
	MenuManage menuManage;
	private String viewPage;
	private String viewPage1;	

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
	@RequestMapping("/sys/listmenu.do")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response ,Menu command) {
		
		Menu model = new Menu();		
		
        List<Menu> menulist = new ArrayList<Menu>();
		
		RollPage rollPage = new RollPage();

		try {
			menulist = menuManage.findMenuList(request, model, rollPage);
			request.setAttribute("list", menulist);
			
		} catch (Exception e) {
			log.error("find Error", e);
		}
		
		return new ModelAndView(this.getViewPage());
	}
	
	@RequestMapping("/sys/savemenu.do")
    public ModelAndView save(HttpServletRequest request,HttpServletResponse response ,Menu command) {
        
		try {
			Menu model = new Menu();
			model.setName(command.getName());
			model.setUrl(command.getUrl());
			model.setFid(command.getFid());
			model.setAscid(command.getAscid());
			model.setStatus(1);
			model.setBz(command.getBz());

			menuManage.save(model);
		} catch (Exception e) {
			log.error("savePrizelist Error", e);
		}
		
		try {
			response.sendRedirect("listmenu.do");
		} catch (IOException e) {
			log.error("sendRedirect Error", e);
		}
		
		return null;
	}
    
	@RequestMapping("/sys/delmenu.do")
    public ModelAndView del(HttpServletRequest request,HttpServletResponse response ,Menu command) {
    	
    	String ids = request.getParameter("ids");
    	ids = ids.replace("'","");
    	ids = ids.substring(0,ids.lastIndexOf(','));
    	System.out.println(ids);
    	
    	try {
			menuManage.delete(ids);
		} catch (Exception e) {
			log.error("del Error", e);
		}
    	
		try {
			response.sendRedirect("listmenu.do");
		} catch (IOException e) {
			log.error("sendRedirect Error", e);
		}
		
		return null;
	}
    
	@RequestMapping("/sys/updatemenu.do")
    public ModelAndView update(HttpServletRequest request,HttpServletResponse response ,Menu command) {
    	
		try {
			Menu model = new Menu();
			model.setId(command.getId());
			model.setName(command.getName());
			model.setUrl(command.getUrl());
			model.setFid(command.getFid());
			model.setAscid(command.getAscid());
			model.setStatus(1);
			model.setBz(command.getBz());

			menuManage.update(model);
		} catch (Exception e) {
			log.error("savePrizelist Error", e);
		}
		
		try {
			response.sendRedirect("listmenu.do");
		} catch (IOException e) {
			log.error("sendRedirect Error", e);
		}
		
		return null;
	}
    
	@RequestMapping("/sys/findmenu.do")
    public ModelAndView find(HttpServletRequest request,HttpServletResponse response ,Menu command) {
    	
    	Integer id = TypeChange.objectToInt(request.getParameter("id"));

    	Menu menu = null;
    	try {
    		menu = menuManage.findById(id);    		
		} catch (Exception e) {
			log.error("savePrizelist Error", e);
		}
		request.setAttribute("model", menu);		
		
    	return new ModelAndView(this.getViewPage1());
	}

}
