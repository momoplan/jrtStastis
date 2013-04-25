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
import com.ruicai.basis.common.BaseController;
import com.ruicai.basis.common.ConvertLang;
import com.ruicai.basis.common.TypeChange;
import com.ruicai.basis.entity.BuyTJ;
import com.ruicai.basis.entity.Channel;
import com.ruicai.basis.entity.Yw;
import com.ruicai.basis.tj.business.BuyTJManage;
import com.ruicai.basis.yw.business.YwManage;

@Controller
public class BuyTJAction{

	private static Log log = LogFactory.getLog(BuyTJAction.class);
	
	private String viewPage;
	
	@Autowired
	private BuyTJManage buyTJManage;
	
	@Autowired
	private YwManage ywManage;
	
	@Autowired
	ChannelManage channelManage;
	
	public String getViewPage() {
		return viewPage;
	}

	public void setViewPage(String viewPage) {
		this.viewPage = viewPage;
	}

	@RequestMapping("/tj/buytj.do")
	public ModelAndView find(HttpServletRequest request,HttpServletResponse response ,BuyTJ command) {
		
		// 初始化
		init(request);
		
		// 初始化选择项
		String ywid = TypeChange.objectToString(request.getParameter("ywid"));
		String channelid = TypeChange.objectToString(request.getParameter("channelid"));
		//System.out.println("ywid : " +ywid);
		//System.out.println("channelid : " +channelid);
		request.setAttribute("ywid", ywid);
		request.setAttribute("channelid", channelid);
		
		// 初始化时间
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
			
		BuyTJ buytj = new BuyTJ();
		buytj.setYwid(ywid);
		buytj.setChannelid(channelid);
		List<BuyTJ> list = new ArrayList<BuyTJ>();		
		try {
			list = buyTJManage.findBuyTJList_all(buytj, beginTime, endTime);
			
		} catch (Exception e) {
			log.error("find Error", e);
		}
		
		BuyTJ tj = new BuyTJ();
		for(int i = 0; i<list.size(); i++){
			BuyTJ model = (BuyTJ)list.get(i);
			tj.setUsernum(tj.getUsernum()+model.getUsernum());
			tj.setBuymoney(tj.getBuymoney()+model.getBuymoney());
			
			Yw yw = ywManage.findByCode(model.getYwid());
			// old:
			//list.get(i).setYwid(yw.getName());
			// wnx update on 20110720:
			Integer ywId = new Integer(list.get(i).getYwid());
			Yw zjYw = ywManage.findById(ywId);
			if(zjYw != null ){
				list.get(i).setYwid(zjYw.getName());
			}
			
			// old:
			//list.get(i).setChannelid(channel.getName());
			// wnx update on 20110720:
			Integer a = new Integer(list.get(i).getChannelid());
			Channel c = channelManage.findById(a);
			if(c != null)
				list.get(i).setChannelid(c.getName());
		}
			
		request.setAttribute("tj", tj);
		request.setAttribute("list", list);
        
		return new ModelAndView(this.getViewPage());
	}
	
    public void init(HttpServletRequest request){
		
		// 业务列表

        List<Yw> ywllist = new ArrayList<Yw>();

		try {
			ywllist = ywManage.findAll();
			request.setAttribute("ywlist", ywllist);
			
		} catch (Exception e) {
			log.error("init ywlist Error", e);
		}	
		
		// 业务列表
        List<Channel> channellist = new ArrayList<Channel>();

		try {
			channellist = channelManage.findAll();
			request.setAttribute("channellist", channellist);
			
		} catch (Exception e) {
			log.error("init channellist Error", e);
		}
	}

}
