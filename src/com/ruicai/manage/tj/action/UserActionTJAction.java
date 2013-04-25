package com.ruicai.manage.tj.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ruicai.basis.common.ConvertLang;
import com.ruicai.basis.entity.UserActionTJ;
import com.ruicai.basis.tj.business.UserActionTJManage;

@Controller
public class UserActionTJAction{

	private static Log log = LogFactory.getLog(UserActionTJAction.class);
	
	private String viewPage;
	
	@Autowired
	UserActionTJManage userActionTJManage;
	
	public String getViewPage() {
		return viewPage;
	}

	public void setViewPage(String viewPage) {
		this.viewPage = viewPage;
	}

	@RequestMapping("/tj/useractiontj.do")
	public ModelAndView find(HttpServletRequest request,HttpServletResponse response ,UserActionTJ command) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String beginTime = "";	
		String endTime = "";
		String channel=null;
		if(request.getParameter("channel")!=null&&!request.getParameter("channel").toString().equals("")){
			channel=request.getParameter("channel").trim();
			try {
				if(!userActionTJManage.findChannelByCode(channel.trim())){
					out.println("<script>window.alert('未知渠道号');window.history.go(-1);</script>");
					return null;
				}
			
				
			} catch (Exception e) {
				out.println("<script>window.alert('未知渠道号');window.history.go(-1);</script>");
				return null;
			}
			request.setAttribute("channel",channel);
			
		}else{
			request.setAttribute("channel","");
		}
		
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
		Date d=new Date();
		SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
		String date=s.format(d);
		Calendar c1=Calendar.getInstance();
		Calendar c2=Calendar.getInstance();
		try {
			c1.setTime(s.parse(endTime));
			c2.setTime(s.parse(date));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		int result=c1.compareTo(c2);
		if(result>0){
			out.println("<script>window.alert('时间超出范围');window.history.go(-1);</script>");
			return null;
			
		}
		request.setAttribute("beginTime", beginTime);
		request.setAttribute("endTime", endTime);
			
		UserActionTJ userActionTJ = new UserActionTJ();		
		List l=new ArrayList();
		UserActionTJ tj = new UserActionTJ();
		try {
			if (request.getParameter("endTime")!= null) {
				 l=userActionTJManage.findUserActionTJ(beginTime,endTime,channel);
			}else {
				l=userActionTJManage.findUserActionTJ(beginTime,ConvertLang.nextTime(endTime),channel); 
			}
			
			for(int i=0;i<l.size();i++){
				HashMap hs=(HashMap)l.get(i);
				tj.setIndexvn(tj.getIndexvn()+Integer.parseInt(hs.get("INDEXVN").toString()));
				tj.setPopvn(tj.getPopvn()+Integer.parseInt(hs.get("POPVN").toString()));
				tj.setPayvn(tj.getPayvn()+Integer.parseInt(hs.get("PAYVN").toString()));
				tj.setPaymoney(tj.getPaymoney()+Float.parseFloat(hs.get("PAYMONEY").toString()));
				tj.setRealPaymoney(tj.getRealPaymoney()+Float.parseFloat(hs.get("REALPAYMONEY").toString()));
				tj.setPsoftn(tj.getPsoftn()+Integer.parseInt(hs.get("PSOFTN").toString()));
				tj.setLoginn(tj.getLoginn()+Integer.parseInt(hs.get("LOGINN").toString()));
				tj.setGetmoneyn(tj.getGetmoneyn()+Integer.parseInt(hs.get("GETMONEYN").toString()));
				tj.setBuyn(tj.getBuyn()+Integer.parseInt(hs.get("BUYN").toString()));
				tj.setPayn(tj.getPayn()+Integer.parseInt(hs.get("PAYN").toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("find Error", e);
		}
		
	
		
		request.setAttribute("tj", tj);
		request.setAttribute("list", l);

		return new ModelAndView(this.getViewPage());
	}

}
