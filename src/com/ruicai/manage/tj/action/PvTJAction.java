package com.ruicai.manage.tj.action;

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
import com.ruicai.basis.common.TypeChange;
import com.ruicai.basis.entity.Channel;
import com.ruicai.basis.entity.PvTJ;
import com.ruicai.basis.entity.Yw;
import com.ruicai.basis.tj.business.PvTJManage;
import com.ruicai.basis.yw.business.YwManage;

@Controller
public class PvTJAction {

	private static Log log = LogFactory.getLog(PvTJAction.class);
	@Autowired
	PvTJManage pvtjManage;
	@Autowired
	YwManage ywManage;
	@Autowired
	ChannelManage channelManage;
	private String viewPage;
	
	public String getViewPage() {
		return viewPage;
	}

	public void setViewPage(String viewPage) {
		this.viewPage = viewPage;
	}
	
	@RequestMapping("/tj/pvtj.do")
	public ModelAndView find(HttpServletRequest request,HttpServletResponse response ,PvTJ command) {
		
		// 初始化
		init(request);
		
		// 初始化选择项
		String ywid = TypeChange.objectToString(request.getParameter("ywid"));
		String channelid = TypeChange.objectToString(request.getParameter("channelid"));
		String province = TypeChange.objectToString(request.getParameter("province"));
		//System.out.println("ywid : " +ywid);
		//System.out.println("channelid : " +channelid);
		request.setAttribute("ywid", ywid);
		request.setAttribute("channelid", channelid);
		request.setAttribute("province", province);
		
		// 初始化时间
		String beginTime = "";	
		String endTime = "";
		
		//初始化排序
		String orderLine = request.getParameter("orderLine")==null?"visitnum":request.getParameter("orderLine");
		String orderRule = request.getParameter("orderRule")==null?"desc":request.getParameter("orderRule");
		request.setAttribute("orderLine", orderLine);
		request.setAttribute("orderRule", orderRule);
		
		
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
		
		// 获取列表
		
		PvTJ pvtj = new PvTJ();
		pvtj.setYwid(ywid);
		pvtj.setChannelid(channelid);
		pvtj.setProvince(province);
		
		List<PvTJ> list = new ArrayList<PvTJ>();
		
		try {
			list = pvtjManage.findPvTJList_all(pvtj, beginTime, endTime, orderLine, orderRule);
			
		} catch (Exception e) {
			log.error("find Error", e);
		}
		
		PvTJ tj = new PvTJ();
		for(int i = 0; i<list.size(); i++){
			PvTJ model = (PvTJ)list.get(i);
			tj.setVisitnum(tj.getVisitnum()+model.getVisitnum());
			tj.setRegnum(tj.getRegnum()+model.getRegnum());
			tj.setPaynum(tj.getPaynum()+model.getPaynum());
			tj.setUvnum(tj.getUvnum()+model.getUvnum());
//			Yw yw = ywManage.findByCode(model.getYwid());
//			list.get(i).setYwid(yw.getName());
			
//			Channel fchannel = new Channel();
//			fchannel.setYw(yw);
//			fchannel.setCode(model.getChannelid());
//			Channel channel = channelManage.findByExample(fchannel);
//			list.get(i).setChannelid(channel.getName());
		}		

		tj.setPercent((float)(tj.getRegnum()*100)/tj.getVisitnum());
		
		request.setAttribute("tj", tj);
		request.setAttribute("list", list);

		return new ModelAndView(this.getViewPage());
	}
	
	public void init(HttpServletRequest request){

        List<Yw> ywllist = new ArrayList<Yw>();

		try {
			ywllist = ywManage.findAll();
			request.setAttribute("ywlist", ywllist);
			
		} catch (Exception e) {
			log.error("init ywlist Error", e);
		}

        List<Channel> channellist = new ArrayList<Channel>();

		try {
			channellist = channelManage.findAll();
			request.setAttribute("channellist", channellist);
			
		} catch (Exception e) {
			log.error("init channellist Error", e);
		}
	}

}
