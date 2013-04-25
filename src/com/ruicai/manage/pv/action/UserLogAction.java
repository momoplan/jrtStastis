package com.ruicai.manage.pv.action;

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

import com.ruicai.basis.common.StringUtils;
import com.ruicai.basis.entity.UserLog;
import com.ruicai.basis.pv.business.UserLogManage;

@Controller
public class UserLogAction {

	private static Log log = LogFactory.getLog(UserLogAction.class);
	
	@Autowired
	UserLogManage userLogManage;
	
	private String viewPage;
	
	public String getViewPage() {
		return viewPage;
	}

	public void setViewPage(String viewPage) {
		this.viewPage = viewPage;
	}


	/**
	 * 保存userlog日志
	 */
	@RequestMapping("/pv/saveuserlog.do")
	public void save(HttpServletRequest request, HttpServletResponse response) {
		
		String sessionid = StringUtils.notNull(request.getParameter("sessionid"));	
		String cn = StringUtils.notNull(request.getParameter("cn"));
		String telnum = StringUtils.ltrim("86", StringUtils.notNull(request.getParameter("mnd")));
		String province = StringUtils.notNull(StringUtils.toUtf8String(request.getParameter("prov")));
		String city = StringUtils.notNull(StringUtils.toUtf8String(request.getParameter("city")));
		String ua = StringUtils.notNull(request.getParameter("ua"));
		Calendar cal = Calendar.getInstance();
		
		UserLog userlog = new UserLog();
		userlog.setSession_id(sessionid);
		userlog.setLog_time(cal.getTime());
		userlog.setCn_num(cn);
		userlog.setTel_num(telnum);
		userlog.setProvince_name(province);
		
		userlog.setCity_name(city);
		userlog.setTel_ua(ua);
		log.info("接受参数:["+userlog+"]");
		try {
			userLogManage.save(userlog);
			log.error("UserLog 保存成功" );
		} catch (Exception e) {
			e.printStackTrace();
			log.error("UserLog 保存错误 ：" + e.getMessage());
		}
	}
	
	/**
	 * 返回最近200条 userlog
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/pv/listuserlog.do")
    public ModelAndView list(HttpServletRequest request,HttpServletResponse response) {		
		
        List<UserLog> userloglist = new ArrayList<UserLog>();

		try {
			userloglist = userLogManage.findLast200List();
			request.setAttribute("list", userloglist);
			
		} catch (Exception e) {
			log.error("find Error", e);
		}
		
		return new ModelAndView(this.getViewPage());
	}   

}
