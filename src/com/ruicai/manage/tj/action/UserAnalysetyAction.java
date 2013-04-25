package com.ruicai.manage.tj.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ruicai.basis.common.BaseController;
import com.ruicai.basis.common.ConvertLang;
import com.ruicai.basis.entity.BuyTJ;
import com.ruicai.basis.entity.UserAnalysety;
import com.ruicai.basis.tj.business.UserAnalysetyManage;

@Controller
public class UserAnalysetyAction{

	private static Log log = LogFactory.getLog(UserAnalysetyAction.class);
	
	private String viewPage;
	
	@Autowired
	UserAnalysetyManage userAnalysetyManage ;
	
	public String getViewPage() {
		return viewPage;
	}

	public void setViewPage(String viewPage) {
		this.viewPage = viewPage;
	}

	
	@RequestMapping("/tj/useranalysety.do")
	public ModelAndView find(HttpServletRequest request,HttpServletResponse response ,BuyTJ command) {
		
		String beginTime = "";	
		String endTime = "";
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		beginTime = ConvertLang.convertDateTimeYMD(cal.getTime());
		cal.add(Calendar.DATE, +1);
		endTime = ConvertLang.convertDateTimeYMD(cal.getTime());
		
		if(request.getParameter("beginTime")!= null){
			beginTime = request.getParameter("beginTime");
		}
		if(request.getParameter("endTime")!= null){
			endTime = request.getParameter("endTime");
		}
		
		request.setAttribute("beginTime", beginTime);
		request.setAttribute("endTime", endTime);
			
		UserAnalysety userAnalysety = new UserAnalysety();		
		List<UserAnalysety> list = new ArrayList<UserAnalysety>();		
		try {
			list = userAnalysetyManage.findUserAnalysetyList_all(userAnalysety, beginTime, endTime);
			
		} catch (Exception e) {
			log.error("find Error", e);
		}
		
		Iterator<UserAnalysety> it = list.iterator();
		UserAnalysety tj = new UserAnalysety();
		while (it.hasNext()) {
			UserAnalysety model = (UserAnalysety) it.next();
	
			tj.setRegnum(tj.getRegnum()+model.getRegnum());
			tj.setSilentnum(tj.getSilentnum()+model.getSilentnum());
			tj.setActivenum(tj.getActivenum()+model.getActivenum());
			tj.setVipnum(tj.getVipnum()+model.getVipnum());
			tj.setEscapenum(tj.getEscapenum()+model.getEscapenum());
		}
		
		request.setAttribute("tj", tj);
		request.setAttribute("list", list);

		return new ModelAndView(this.getViewPage());
	}

}
