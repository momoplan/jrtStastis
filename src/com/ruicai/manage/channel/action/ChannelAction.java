package com.ruicai.manage.channel.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

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
import com.ruicai.basis.common.RollPage;
import com.ruicai.basis.common.TypeChange;
import com.ruicai.basis.entity.Channel;
import com.ruicai.basis.entity.User;
import com.ruicai.basis.entity.Yw;
import com.ruicai.basis.yw.business.YwManage;

@Controller
public class ChannelAction{

	private static Log log = LogFactory.getLog(ChannelAction.class);
	
	private String viewPage;
	private String viewPage1;	
	private String viewPage2;	
	private String viewPage3;	
    private String viewPageCooperate;//合作方式的统计页面
    
    @Autowired
    ChannelManage channelManage;
    
    @Autowired
    YwManage ywManage;
    
	public String getViewPageCooperate() {
		return viewPageCooperate;
	}

	public void setViewPageCooperate(String viewPageCooperate) {
		this.viewPageCooperate = viewPageCooperate;
	}

	public String getViewPage3() {
		return viewPage3;
	}

	public void setViewPage3(String viewPage3) {
		this.viewPage3 = viewPage3;
	}

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

	@RequestMapping("/channel/listchannel.do")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response ,Channel command) {
		
		Integer ywid = TypeChange.objectToInt(request.getParameter("ywid"));
		/**
		 * 是统计金额的日期
		 */
		// 初始化时间
		String startTime = "";	
		String endTime = "";
		Calendar cal = Calendar.getInstance();
		startTime = ConvertLang.convertDateTimeYMD(cal.getTime());
		cal.add(Calendar.DATE, +1);
		endTime = ConvertLang.convertDateTimeYMD(cal.getTime());
		
		if(request.getParameter("startTime")!= null){
			startTime = request.getParameter("startTime");
		}
		if(request.getParameter("endTime")!= null){
			endTime = request.getParameter("endTime");
		}
		log.info("=================startTime==="+startTime);
		log.info("=================endTime==="+endTime);
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);
		
		
		Yw yw = new Yw();
		yw.setId(ywid);
		yw.setEndTime(endTime);
		yw.setStartTime(startTime);
		
		Channel model = new Channel();
		model.setYw(yw);	
		request.setAttribute("ywid", ywid);
		
		// 业务列表

        List<Yw> ywllist = new ArrayList<Yw>();

		try {
			ywllist = ywManage.findAll();
			request.setAttribute("ywlist", ywllist);
			
		} catch (Exception e) {
			log.error("init ywlist Error", e);
		}	
		
        List<Channel> channellist = new ArrayList<Channel>();
		
		RollPage rollPage = new RollPage();

		try {
			channellist = channelManage.findList(request, model, rollPage);
			request.setAttribute("list", channellist);
			
		} catch (Exception e) {
			log.error("find Error", e);
		}
		
		return new ModelAndView(this.getViewPage());
	}
	
	@RequestMapping("/channel/savechannel.do")
	 public ModelAndView save(HttpServletRequest request,HttpServletResponse response ,Channel command) {
	    	response.setContentType("text/html; charset=utf-8");
			PrintWriter out=null;
			try {
				out = response.getWriter();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	User operator = (User) request.getSession().getAttribute("user");
	    	Integer ywid = TypeChange.objectToInt(request.getParameter("ywid"));
			try {
				Calendar cal = Calendar.getInstance();
				
				Yw yw = new Yw();
				yw.setId(ywid);
				User user = new User();
				if(operator != null){
					user.setId(operator.getId());
				}
				
				Channel model = new Channel();
				model.setYw(yw);
				model.setCode(command.getCode().trim());
				model.setName(command.getName());
				model.setUser(user);
				model.setCjdate(cal.getTime());
				model.setStatus(1);
				model.setTel(command.getTel());
				model.setBz(command.getBz());
				model.setUrl(command.getUrl());
				List<Channel> channel=channelManage.findByCode(model);
				if(channel.size()>0){
					out.println("<script>window.alert('渠道号已存在');window.history.go(-1);</script>");
					return null;
					
				}
				String cooperat_type=request.getParameter("cooperat_type");
		    	String count_type=request.getParameter("count_type0");
		    	String count=request.getParameter("count0");
		    	
		    	if(Pattern.compile("[0-9]+[.][0-9]+").matcher(count).matches()==false&&Pattern.compile("[0-9]+").matcher(count).matches()==false){
		    		out.println("<script>window.alert('金额不合法');window.history.go(-1);</script>");
		    		return null;
		    		
		    	}
		    	String count1=" ";
		    	String count_type1=" ";
		    	HashMap type=new HashMap();
		    	type.put("1","CPA");
		    	type.put("2","CPS");
		    	type.put("3","CPC");
		    	type.put("4","CPA");
		    	type.put("5","RIVET");
		    	type.put("type1","元/个");
		    	type.put("type2","元/月");
		    	type.put("type3","百分比");
		    	HashMap hs=new HashMap();
		    	hs.put("CHANNEL_ID",command.getCode());
		    	hs.put("COOPERAT_TYPE",type.get(cooperat_type));
		    	hs.put("COUNT_TYPE",type.get(count_type));
		    	hs.put("COUNT",count);
		    	
		    	
		    	channelManage.createCooperat(hs);
		    	if(cooperat_type.equals("4")){
		    		 count1=request.getParameter("count1");
		    			if(Pattern.compile("[0-9]+[.][0-9]+").matcher(count1).matches()==false&&Pattern.compile("[0-9]+").matcher(count1).matches()==false){
		    	    		out.println("<script>window.alert('金额不合法');window.history.go(-1);</script>");
		    	    		return null;
		    	    		
		    	    	}
		    		 count_type1=request.getParameter("count_type1");
		    		 hs.put("COOPERAT_TYPE","CPS");
		    		 hs.put("COUNT",count1);
		    		 hs.put("COUNT_TYPE",type.get(count_type1));
		    		 channelManage.createCooperat(hs);
		    	}
		    	channelManage.save(model);
			} catch (Exception e) {
				log.error("save Error", e);
			}
			
			try {
				response.sendRedirect("listchannel.do?ywid="+ywid);
			} catch (IOException e) {
				log.error("sendRedirect Error", e);
			}
			
			return null;
		}
    
	@RequestMapping("/channel/delchannel.do")
    public ModelAndView del(HttpServletRequest request,HttpServletResponse response ,Channel command) {
    	
    	String ids = request.getParameter("ids");
    	ids = ids.replace("'","");
    	ids = ids.substring(0,ids.lastIndexOf(','));
    	
    	try {
    		channelManage.delete(ids);
		} catch (Exception e) {
			log.error("del Error", e);
		}
    	
		try {
			response.sendRedirect("listchannel.do");
		} catch (IOException e) {
			log.error("sendRedirect Error", e);
		}
		
		return null;
	}
    
	@RequestMapping("/channel/updatechannel.do")
    public ModelAndView update(HttpServletRequest request,HttpServletResponse response ,Channel command) {
    	response.setContentType("text/html; charset=utf-8");
		PrintWriter out=null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	Integer ywid = TypeChange.objectToInt(request.getParameter("ywid"));
    		
		try {
			Channel model = channelManage.findById(command.getId());
			
			Yw yw = model.getYw();
			yw.setId(ywid);
			
			model.setYw(yw);
			model.setCode(command.getCode());
			model.setName(command.getName());		
			model.setStatus(1);
			model.setTel(command.getTel());
			model.setBz(command.getBz());
			model.setUrl(command.getUrl());
			String cooperat_type=request.getParameter("cooperat_type");
	    	String count_type=request.getParameter("count_type0");
	    	String count=request.getParameter("count0");
	    	String code1=request.getParameter("code1");
	    	String count1=" ";
	    	String count_type1=" ";

	    	channelManage.delCooperat(code1);	
	    	HashMap type=new HashMap();
	    	type.put("1","CPA");
	    	type.put("2","CPS");
	    	type.put("3","CPC");
	    	type.put("4","CPA");
	    	type.put("5","RIVET");
	    	type.put("type1","元/个");
	    	type.put("type2","元/月");
	    	type.put("type3","百分比");
	    	HashMap hs=new HashMap();
	    	hs.put("CHANNEL_ID",command.getCode());
	    	hs.put("COOPERAT_TYPE",type.get(cooperat_type));
	    	hs.put("COUNT_TYPE",type.get(count_type));
	    	hs.put("COUNT",count);
	    	if(Pattern.compile("[0-9]+.[0-9]+").matcher(count).matches()==false&&Pattern.compile("[0-9]+").matcher(count).matches()==false){
	    		out.println("<script>window.alert('金额不合法');window.history.go(-1);</script>");
	    		return null;
	    		
	    	}
	    	channelManage.createCooperat(hs);
	    	if(cooperat_type.equals("4")){
	    		 count1=request.getParameter("count1");
	    			if(Pattern.compile("[0-9]+[.][0-9]+").matcher(count1).matches()==false&&Pattern.compile("[0-9]+").matcher(count1).matches()==false){
	    	    		out.println("<script>window.alert('金额不合法');window.history.go(-1);</script>");
	    	    		return null;
	    	    		
	    	    	}
	    		 count_type1=request.getParameter("count_type1");
	    		 hs.put("COOPERAT_TYPE","CPS");
	    		 hs.put("COUNT_TYPE",type.get(count_type1));
	    		 hs.put("COUNT",count1);
	    		 channelManage.createCooperat(hs);
	    	}
	    	channelManage.update(model);
		} catch (Exception e) {
			log.error("update Error", e);
		}
		
		try {
			response.sendRedirect("listchannel.do?ywid="+ywid);
		} catch (IOException e) {
			log.error("sendRedirect Error", e);
		}
		
		return null;
	}
    
	@RequestMapping("/channel/findchannel.do")
    public ModelAndView find(HttpServletRequest request,HttpServletResponse response ,Channel command) {
    	
    	Integer id = TypeChange.objectToInt(request.getParameter("id"));
    	
    	List<Yw> ywllist = new ArrayList<Yw>();

 		try {
 			ywllist = ywManage.findAll();
 			request.setAttribute("list", ywllist);
 			
 		} catch (Exception e) {
 			log.error("find ywllist Error", e);
 		}		

    	Channel model = null;
    	try {
    		model = channelManage.findById(id);    		
		} catch (Exception e) {
			log.error("find Channel Error", e);
		}
		request.setAttribute("model", model);	
		HashMap hs=new HashMap();
		String channel_id=request.getParameter("code");
		hs.put("CHANNL_ID",channel_id);
		List l=channelManage.findCooperatByChannelId(hs);
		request.setAttribute("cooperat", l);
    	return new ModelAndView(this.getViewPage1());
	}
	
	@RequestMapping("/channel/find1channel.do")
    public ModelAndView find1channel(HttpServletRequest request,HttpServletResponse response ,Channel command) {
    	
    	Integer id = TypeChange.objectToInt(request.getParameter("id"));
    	List<Yw> ywllist = new ArrayList<Yw>();
    	try {
    		ywllist = ywManage.findAll();
    		request.setAttribute("list", ywllist);
    		
    	} catch (Exception e) {
    		log.error("find ywllist Error", e);
    	}		
    	Channel model = null;
    	try {
    		model = channelManage.findById(id);    		
    	} catch (Exception e) {
    		log.error("find Channel Error", e);
    	}
    	request.setAttribute("model", model);	
    	HashMap hs=new HashMap();
    	String channel_id=request.getParameter("code");
    	hs.put("CHANNL_ID",channel_id);
    	List l=channelManage.findCooperatByChannelId(hs);
    	request.setAttribute("cooperat", l);
    	return new ModelAndView(this.getViewPage3());
    }
    
    @RequestMapping("/channel/newchannel.do")
    public ModelAndView newchannel(HttpServletRequest request,HttpServletResponse response ,Channel command) {
    	
        List<Yw> ywllist = new ArrayList<Yw>();
        List<Channel> channelllist = new ArrayList<Channel>();
        StringBuffer str=new StringBuffer();
		try {
			channelllist=channelManage.findAll();
			for(int i=0;i<channelllist.size();i++){
				str.append(channelllist.get(i).getCode()).append(",");
			}
			ywllist = ywManage.findAll();
			request.setAttribute("list", ywllist);
			request.setAttribute("channelcodelist",str);
		} catch (Exception e) {
			log.error("newchannel Error", e);
		}		
		
    	return new ModelAndView(this.getViewPage2());
	}
    
    /**
     * 渠道的合作模式页面
       @author       丁俊杰
       @createDate   2010-11-9
       @param request
       @param response
       @param command
       @return
     */
    @RequestMapping("/channel/findCooperatechannel.do")
    public ModelAndView findCooperate(HttpServletRequest request,
			HttpServletResponse response, Channel command) {
         log.info("========================渠道action 合作方式的方法开始===");
		try {
            /**
             * 在serivce中进行业务逻辑的处理
             */
			channelManage.cooperatList(request);
		} catch (Exception e) {
			log.error("========findCooperate  faile" + e);
		} finally {
			log.info("========================渠道action 合作方式的方法结束===");
		}
		return new ModelAndView(this.getViewPageCooperate());
	 }
   }
