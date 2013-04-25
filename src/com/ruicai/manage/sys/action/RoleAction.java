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
import com.ruicai.basis.entity.Channel;
import com.ruicai.basis.entity.Menu;
import com.ruicai.basis.entity.Role;
import com.ruicai.basis.entity.RoleMenu;
import com.ruicai.basis.sys.business.MenuManage;
import com.ruicai.basis.sys.business.RoleManage;
@Controller
public class RoleAction{

	private static Log log = LogFactory.getLog(RoleAction.class);

	@Autowired
	RoleManage roleManage;
	@Autowired
	MenuManage menuManage;
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

	@RequestMapping("/sys/listrole.do")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response, Role command) {

		Role model = new Role();

		List<Role> ywlist = new ArrayList<Role>();

		RollPage rollPage = new RollPage();

		try {
			ywlist = roleManage.findList(request, model, rollPage);
			request.setAttribute("list", ywlist);

		} catch (Exception e) {
			log.error("find Error", e);
		}

		return new ModelAndView(this.getViewPage());
	}

	@RequestMapping("/sys/saverole.do")
	public ModelAndView save(HttpServletRequest request,
			HttpServletResponse response, Role command) {
		String[] autos=request.getParameterValues("PRESTR");

		try {
			Role model = new Role();

			model.setName(command.getName());
			model.setStatus(1);
			model.setBz(command.getBz());

			roleManage.save(model,autos);
		} catch (Exception e) {
			log.error("save Role Error", e);
		}

		/*
		System.out.println("111 "+autos.length);
		for(String s : autos){
			System.out.println(s);
		}
		
		Cookie cookies[] = request.getCookies();
		Cookie sCookie = null;
		String svalue = null;
		String sname = null;
		for (int i = 0; i < cookies.length; i++) {
			sCookie = cookies[i];
			svalue = sCookie.getValue();
			sname = sCookie.getName();
		}*/

		try {
			response.sendRedirect("listrole.do");
		} catch (IOException e) {
			log.error("sendRedirect Error", e);
		}

		return null;
	}

	@RequestMapping("/sys/delrole.do")
	public ModelAndView del(HttpServletRequest request,
			HttpServletResponse response, Role command) {

		String ids = request.getParameter("ids");
		ids = ids.replace("'", "");
		ids = ids.substring(0, ids.lastIndexOf(','));

		try {
			roleManage.deleteJL(ids);
		} catch (Exception e) {
			log.error("del Error", e);
		}

		try {
			response.sendRedirect("listrole.do");
		} catch (IOException e) {
			log.error("sendRedirect Error", e);
		}

		return null;
	}

	@RequestMapping("/sys/updaterole.do")
	public ModelAndView update(HttpServletRequest request,
			HttpServletResponse response, Role command) {
		String[] autos=request.getParameterValues("PRESTR");

		try {
			Role model = roleManage.findById(command.getId());

			model.setName(command.getName());
			model.setBz(command.getBz());

			roleManage.update(model,autos);
		} catch (Exception e) {
			log.error("update Error", e);
		}		
		
		try {
			response.sendRedirect("listrole.do");
		} catch (IOException e) {
			log.error("sendRedirect Error", e);
		}

		return null;
	}

	@RequestMapping("/sys/findrole.do")
	public ModelAndView find(HttpServletRequest request,
			HttpServletResponse response, Role command) {
		Integer id = TypeChange.objectToInt(request.getParameter("id"));

		Role model = null;
		try {
			model = roleManage.findById(id);
		} catch (Exception e) {
			log.error("find Error", e);
		}
		request.setAttribute("model", model);
		
		List<RoleMenu> list = null;
		try {
			list = roleManage.findRoleMenu(id);
		} catch (Exception e) {
			log.error("RoleMenu find Error", e);
		}
		request.setAttribute("list", list);


		return new ModelAndView(this.getViewPage1());
	}

	@RequestMapping("/sys/newrole.do")
	public ModelAndView newrole(HttpServletRequest request,
			HttpServletResponse response, Channel command) {
		
		List<Menu> list = null;
		try {
			list = menuManage.findAll();
		} catch (Exception e) {
			log.error("Menu find Error", e);
		}
		request.setAttribute("list", list);

		return new ModelAndView(this.getViewPage2());
	}

}
