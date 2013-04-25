package com.ruicai.manage.pv.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ruicai.basis.common.TypeChange;
import com.ruicai.basis.entity.PVCount;
import com.ruicai.basis.entity.TUserInfo;
import com.ruicai.basis.hezuo.business.TUserInfoManage;
import com.ruicai.basis.pv.business.PVCountManage;

@Controller
public class PVCountAction {

	private static Log log = LogFactory.getLog(PVCountAction.class);
	
	private String viewPage;
	private String viewPage1;
	
	@Autowired
	PVCountManage pvCountManage;
	
	@Autowired
	TUserInfoManage tuserInfoManage;
	
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

	/**
	 * 接受PV参数
	 */
	@RequestMapping("/pv/savepv.do")
	public void save(HttpServletRequest request, HttpServletResponse response) { 
		Calendar cal = Calendar.getInstance();
		Integer type = 0;
		String channelId = "0"; 
		try { 
			JSONObject jo = JSONObject.fromObject(request.getParameter("jsonString"));// 获取请求信息
			type = Integer.valueOf(jo.getString("type").trim()); // 获取统计页面的类型			
				
			if(jo.containsKey("channelId")){
				channelId = jo.getString("channelId");	
			}else
			if(jo.containsKey("channelid")){
				channelId = jo.getString("channelid");	
			}
			
			PVCount pvcount = new PVCount();
			pvcount.setTsj(cal.getTime());
			pvcount.setType(type);
			pvcount.setNum(1);
			pvcount.setChannelid(channelId);
			if (type > 0) {
				pvCountManage.save(pvcount);
			}
		} catch (Exception e) {
			System.out.println("error jsonString json:"+request.getParameter("jsonString"));
			e.printStackTrace();
			log.error("PVCount 保存错误 ：" + e.getMessage());
		}

	}
	
	/**
	 * 返回最近200条 PV log
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/pv/listpv.do")
    public ModelAndView list(HttpServletRequest request,HttpServletResponse response) {		
		
		
        List<PVCount> pvcountlist = new ArrayList<PVCount>();

		try {
			pvcountlist = pvCountManage.findList();
			request.setAttribute("list", pvcountlist);
			
		} catch (Exception e) {
			log.error("find Error", e);
		}
		
		return new ModelAndView(this.getViewPage());
	}
    
	@RequestMapping("/pv/userpv.do")
    public ModelAndView user(HttpServletRequest request,HttpServletResponse response) {		
		
    	
    	Integer beginnum = TypeChange.objectToInt(request.getParameter("beginnum"));
    	Integer endnum = TypeChange.objectToInt(request.getParameter("endnum"));
		
        List<TUserInfo> pvcountlist = new ArrayList<TUserInfo>();

		try {
			pvcountlist = tuserInfoManage.findTUserInfoList(beginnum, endnum);
			request.setAttribute("list", pvcountlist);
			
		} catch (Exception e) {
			log.error("find Error", e);
		}
		
		return new ModelAndView(this.getViewPage1());
	}	

}
