package com.ruicai.manage.userinfo.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruicai.basis.channel.business.ChannelManage;
import com.ruicai.basis.common.ConvertLang;
import com.ruicai.basis.common.RollPage;
import com.ruicai.basis.common.StringUtils;
import com.ruicai.basis.common.TypeChange;
import com.ruicai.basis.entity.Channel;
import com.ruicai.basis.entity.Tbl_userinfo;
import com.ruicai.basis.entity.UserInfo;
import com.ruicai.basis.entity.UserTransAction;
import com.ruicai.basis.tj.dao.ClientZjlTJDAO;
import com.ruicai.basis.userinfo.business.UserInfoManage;
import com.ruicai.basis.userinfo.dao.UserInfoDAO;
import com.ruicai.basis.util.DateUtil;

@Controller
public class UserdtAction{

	private static Log log = LogFactory.getLog(UserdtAction.class);
	
	private String viewPage;
	
	@Autowired
	UserInfoManage userInfoManage;
	
	@Autowired
	ChannelManage channelManage;

	public String getViewPage() {
		return viewPage;
	}

	public void setViewPage(String viewPage) {
		this.viewPage = viewPage;
	}	

	private String viewPage1;
	
	public String getViewPage1() {
		return viewPage1;
	}

	public void setViewPage1(String viewPage1) {
		this.viewPage1 = viewPage1;
	}	
	
	/**
	 * 查询用户信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/userinfo/finduserdt.do")
    public ModelAndView find(HttpServletRequest request,HttpServletResponse response) {		
		
    	UserInfo model = null;    		
    	String ywname = "";
    	String channelname = "";
    	List<UserTransAction> list = null;
    	String tel = TypeChange.objectToString(request.getParameter("tel"));
    	
    	if(tel!=""){
    		model = userInfoManage.findUserInfo(tel);
    	}    	
    	
    	if(null != model){
    		String ids = model.getChannel().trim();
    		if(ids.length() != 0){
    		   Integer channelid = TypeChange.stringToInt(ids);
    		   Channel chmodel = channelManage.findById(channelid);
    		   if(chmodel!=null){
    		   channelname = chmodel.getName();
    		   ywname = chmodel.getYw().getName();}
    		}   			
    		
    		list = userInfoManage.findUserTransAction(model.getUserno());
    	}
    	
    	request.setAttribute("tel", tel);
    	request.setAttribute("list", list);
    	request.setAttribute("ywname", ywname);
    	request.setAttribute("channelname", channelname);
    	request.setAttribute("model", model);
    	
    	return new ModelAndView(this.getViewPage());
	}     
	@Autowired
	ClientZjlTJDAO clientZjlTJDAO;
	
	@Autowired
	UserInfoDAO userInfoDAO;
	
	/**
	 * 查询用户信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/userinfo/findsnuserdt.do")
    public ModelAndView findsnuserdt(
    		@RequestParam(value = "imei",required=false) String imei,
    		@RequestParam(value = "starttime",required=false) String starttime,
    		@RequestParam(value = "endtime",required=false)  String endtime,
    		HttpServletRequest request) {		
		if (imei == null || "".equals(imei)) {
			request.setAttribute("rollPage", new RollPage());
			return new ModelAndView(this.getViewPage1());
		}
		
		try {
			Tbl_userinfo tbl_userinfo = clientZjlTJDAO.findClientUserByImei(imei);
			if (tbl_userinfo == null) {
				log.info("imei不存在");
				request.setAttribute("errormsg", "imei不存在");
				request.setAttribute("rollPage", new RollPage());
				return new ModelAndView(this.getViewPage1());
			}
			/*if("".equals(tbl_userinfo.getChannel())){
				log.info("channel不存在");
				request.setAttribute("errormsg", "channel不存在");
				request.setAttribute("rollPage", new RollPage());
				return new ModelAndView(this.getViewPage1());
			}*/
			String userno = tbl_userinfo.getUserno();
			String page = request.getParameter("page");
			RollPage rollPage = new RollPage(page == null || page.equals("") ? 0: ConvertLang.convertint(page));
			rollPage.setPagePer(10);
			
			StringBuffer hql = new StringBuffer("from Ttransaction as t where t.type in (2,3,10) and t.userno = ?");
			List<Object> param = new ArrayList<Object>();
			param.add(userno);
			if (!StringUtils.nullOrBlank(starttime)) {
				Date startime = DateUtil.parse(starttime+" 00:00:00");
				if(startime != null){
					hql.append("and t.plattime >= ?");
					param.add(startime);
				}
			}
			Date endttime = DateUtil.parse(endtime+" 23:59:59");
			if(endttime != null){
				if(endttime.after(DateUtil.getCurrentDay())){
					endttime = DateUtil.getCurrentDay();
				}
			}else{
				endttime = DateUtil.getCurrentDay();
			}
			hql.append("and t.plattime <= ?");
			param.add(endttime);
	    	
	    	int resCount =userInfoDAO.findListCount(hql.toString(), param);
			int rescount = resCount/rollPage.getPagePer();
			if(rollPage.getPageCur()>rescount){
				rollPage.setPageCur(0);
			}
			rollPage.setRowCount(resCount);
			rollPage.init();
			
	    	request.setAttribute("rollPage", rollPage);
	    	hql.append(" order by t.plattime desc");
	    	request.setAttribute("list", userInfoDAO.findTtrlist(hql.toString(), param, rollPage.getPageFirst(), rollPage.getPagePer()));
	    	
	    	request.setAttribute("sumAmt", userInfoDAO.findSumAmt("select sum(t.amt) "+hql, param));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
    	return new ModelAndView(this.getViewPage1());
	}     
}
