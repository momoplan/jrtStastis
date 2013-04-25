package com.ruicai.manage.hezuo.action;

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

import com.ruicai.basis.channel.business.ChannelManage;
import com.ruicai.basis.common.ConvertLang;
import com.ruicai.basis.entity.BuyTJ;
import com.ruicai.basis.entity.Channel;
import com.ruicai.basis.entity.PvTJ;
import com.ruicai.basis.entity.TUserInfo;
import com.ruicai.basis.entity.User;
import com.ruicai.basis.entity.Yw;
import com.ruicai.basis.hezuo.business.TUserInfoManage;
import com.ruicai.basis.tj.business.BuyTJManage;
import com.ruicai.basis.tj.business.PvTJManage;
import com.ruicai.basis.yw.business.YwManage;

@Controller
@RequestMapping("/hezuo")
public class HeZuoAction {

	private static Log log = LogFactory.getLog(HeZuoAction.class);

	private String viewPage;
	private String viewPage1;
	private String viewPage2;
	
	@Autowired
	PvTJManage pvtjManage;
	
	@Autowired
	YwManage ywManage ;
	
	@Autowired
	ChannelManage channelManage;
	
	@Autowired
	TUserInfoManage tuserInfoManage;
	
	
	@Autowired
	BuyTJManage buyTJManage;

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

	@RequestMapping("/pvlist.do")
	public ModelAndView pvlist(HttpServletRequest request, HttpServletResponse response) {
		
		User user = (User) request.getSession().getAttribute("user");
		// 初始化时间
		String beginTime = "";	
		String endTime = "";
		
		Calendar cal = Calendar.getInstance();
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
		
        List<PvTJ> list = new ArrayList<PvTJ>();
		
		try {
			list = pvtjManage.findPvTJList_hezuo(user, beginTime, endTime);
			
		} catch (Exception e) {
			log.error("find Error", e);
		}
		
		PvTJ tj = new PvTJ();
		for(int i = 0; i<list.size(); i++){
			PvTJ model = (PvTJ)list.get(i);
			tj.setVisitnum(tj.getVisitnum()+model.getVisitnum());
			tj.setRegnum(tj.getRegnum()+model.getRegnum());
			tj.setPaynum(tj.getPaynum()+model.getPaynum());
			
			Yw yw = ywManage.findById(new Integer(model.getYwid()));
			list.get(i).setYwid(yw.getName());
			
			Channel fchannel = new Channel();
			fchannel.setYw(yw);
			//fchannel.setCode(model.getChannelid());
			fchannel.setId(new Integer(model.getChannelid()));
			//Channel channel = channelManage.findByExample(fchannel);
			Channel channel = channelManage.findById(new Integer(model.getChannelid()));
			list.get(i).setChannelid(channel.getName());
			list.get(i).setChannel(channel.getId());
		}		

		tj.setPercent((float)(tj.getRegnum()*100)/tj.getVisitnum());
		
		request.setAttribute("tj", tj);
		request.setAttribute("list", list);

		return new ModelAndView(this.getViewPage());
	}
	
	@RequestMapping("/regnumlist.do")
	public ModelAndView regnum(HttpServletRequest request, HttpServletResponse response) {
		// 初始化时间
		String time = request.getParameter("time");
		if(time!=null){
			if(time.length()>10)
				time = time.substring(0, 10);
		}
		String channel = request.getParameter("channel");


		List<TUserInfo> list = new ArrayList<TUserInfo>();
		try {
			list = tuserInfoManage.findTUserInfoList(channel,time);
		} catch (Exception e) {
			log.error("find findTUserInfoList error", e);
		}

		request.setAttribute("list", list);

		return new ModelAndView(this.getViewPage1());
	}

	@RequestMapping("/buylist.do")
	public ModelAndView buylist(HttpServletRequest request, HttpServletResponse response) {
		
		
		User user = (User) request.getSession().getAttribute("user");
		
		// 初始化时间
		String beginTime = "";	
		String endTime = "";
		
		Calendar cal = Calendar.getInstance();
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
			
		List<BuyTJ> list = new ArrayList<BuyTJ>();		
		try {
			list = buyTJManage.findBuyTJList_hezuo(user, beginTime, endTime);
			
		} catch (Exception e) {
			log.error("find Error", e);
		}
		
		BuyTJ tj = new BuyTJ();
		for(int i = 0; i<list.size(); i++){
			BuyTJ model = (BuyTJ)list.get(i);
			tj.setUsernum(tj.getUsernum()+model.getUsernum());
			tj.setBuymoney(tj.getBuymoney()+model.getBuymoney());
			
			//Yw yw = ywManage.findByCode(model.getYwid());
			Yw yw = ywManage.findById(new Integer(model.getYwid()));
			list.get(i).setYwid(yw.getName());
			
			//Channel fchannel = new Channel();
			//fchannel.setYw(yw);
			//fchannel.setCode(model.getChannelid());
			//Channel channel = channelManage.findByExample(fchannel);
			Channel channel = channelManage.findById(new Integer(model.getChannelid()));
			list.get(i).setChannelid(channel.getName());
		}
			
		request.setAttribute("tj", tj);
		request.setAttribute("list", list);
		
		return new ModelAndView(this.getViewPage2());
	}
	
}
