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
import com.ruicai.basis.entity.RedTel;
import com.ruicai.basis.sys.business.RedTelManage;

@Controller
public class RedTelAction{

	private static Log log = LogFactory.getLog(RedTelAction.class);
	@Autowired
	RedTelManage redtelManage;
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

	@RequestMapping("/sys/listredtel.do")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response, RedTel command) {

		RedTel model = new RedTel();

		List<RedTel> redtellist = new ArrayList<RedTel>();

		RollPage rollPage = new RollPage();

		try {
			redtellist = redtelManage.findList(request, model, rollPage);

		} catch (Exception e) {
			log.error("find Error", e);
		}

		request.setAttribute("list", redtellist);

		return new ModelAndView(this.getViewPage());
	}

	@RequestMapping("/sys/saveredtel.do")
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response,
			RedTel command) {

		if (!redtelManage.isSingleTel(command.getTel().trim())) {
			request.setAttribute("model", command);
			request.setAttribute("msg", "手机号不能重复");

			return new ModelAndView(this.getViewPage2());

		} else {

			try {

				RedTel model = new RedTel();

				model.setTel(command.getTel().trim());
				model.setBz(command.getBz().trim());

				redtelManage.save(model);
			} catch (Exception e) {
				log.error("save Error", e);
			}

			return list(request,response, command) ;

		}
	}

	@RequestMapping("/sys/delredtel.do")
	public void del(HttpServletRequest request, HttpServletResponse response,
			RedTel command) {

		String ids = request.getParameter("ids");
		ids = ids.replace("'", "");
		ids = ids.substring(0, ids.lastIndexOf(','));

		try {
			redtelManage.delete(ids);
		} catch (Exception e) {
			log.error("del Error", e);
		}

		try {
			response.sendRedirect("listredtel.do");
		} catch (IOException e) {
			log.error("sendRedirect Error", e);
		}
	}

	@RequestMapping("/sys/updateredtel.do")
	public ModelAndView update(HttpServletRequest request,
			HttpServletResponse response, RedTel command) {

		try {
			RedTel model = redtelManage.findById(command.getId());

			//model.setTel(command.getTel());
			model.setBz(command.getBz());

			redtelManage.update(model);
		} catch (Exception e) {
			log.error("update Error", e);
		}

		return list(request,response, command) ;
	}

	@RequestMapping("/sys/findredtel.do")
	public ModelAndView find(HttpServletRequest request,
			HttpServletResponse response, RedTel command) {
		Integer id = TypeChange.objectToInt(request.getParameter("id"));

		RedTel model = null;
		try {
			model = redtelManage.findById(id);
		} catch (Exception e) {
			log.error("find Error", e);
		}
		request.setAttribute("model", model);

		return new ModelAndView(this.getViewPage1());
	}

}
