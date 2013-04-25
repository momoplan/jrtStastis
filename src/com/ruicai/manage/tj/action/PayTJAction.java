package com.ruicai.manage.tj.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruicai.basis.channel.business.ChannelManage;
import com.ruicai.basis.channel.dao.UserCfgDAO;
import com.ruicai.basis.common.ConvertLang;
import com.ruicai.basis.common.TypeChange;
import com.ruicai.basis.entity.Channel;
import com.ruicai.basis.entity.PayTJ;
import com.ruicai.basis.entity.User;
import com.ruicai.basis.entity.UserCfg;
import com.ruicai.basis.entity.Yw;
import com.ruicai.basis.tj.business.PayTJManage;
import com.ruicai.basis.tj.dao.PayTJDAO;
import com.ruicai.basis.util.DateUtil;
import com.ruicai.basis.yw.business.YwManage;

@Controller
public class PayTJAction{

	private static Log log = LogFactory.getLog(PayTJAction.class);
	@Autowired
	PayTJManage payTJManage;
	@Autowired
	YwManage ywManage;
	@Autowired
	ChannelManage channelManage;
	
	@Autowired
	UserCfgDAO userCfgDAO;
	
	@Autowired
	PayTJDAO payTJDAO;
	
	/**
	 * 导出用户充值失败数据
	 * @param request
	 * @param response
	 * @param command
	 * @return
	 */
	@RequestMapping("/tj/paytjExport.do")
	public ModelAndView export(HttpServletRequest request,HttpServletResponse response ,PayTJ command) {
		//初始化
		init(request);
		
		List list = null;
		
		//初始化选择项
		String beginTime = null;
		String endTime = null;
		
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
		try{
			list = payTJManage.findFailedPayTJList_all(beginTime, endTime);
		}catch(Exception e){
			log.error("findFailedPayTJList_all Error", e);
		}
		
		//excel导出数据
		try{
			ExportExcel.exportWithdrawReview(beginTime.replace("-", "_")+"-"+endTime.replace("-", "_")+"用户充值失败记录.xls", list, response);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping("/tj/paytj.do")
	public ModelAndView find(HttpServletRequest request,HttpServletResponse response ,PayTJ command) throws UnsupportedEncodingException {
		// 初始化
		init(request);
		request.setCharacterEncoding("UTF-8");
		// 初始化选择项
		String ywid = TypeChange.objectToString(request.getParameter("ywid"));
		String flag="1";
		String channelid = TypeChange.objectToString(request.getParameter("channelid"));
		ywid = URLDecoder.decode(ywid, "UTF-8");
		List<Channel> channellist = new ArrayList<Channel>();
		if(!StringUtils.isEmpty(request.getParameter("flag"))){
			flag=request.getParameter("flag");
		}
		if(flag.equals("2")){
			HashMap hs=new HashMap();
		try {
			channellist = channelManage.findAll();
			for(int i=0;i<channellist.size();i++){
				hs.put(channellist.get(i).getName(),channellist.get(i).getCode());
			}
			channelid=hs.get(URLDecoder.decode(channelid,"UTF-8")).toString();
			List<Yw> yw = new ArrayList<Yw>();
			yw=ywManage.findAll();
			for(int i=0;i<yw.size();i++){
				hs.put(yw.get(i).getName(),yw.get(i).getCode());
			}
			ywid=hs.get(URLDecoder.decode(ywid,"UTF-8")).toString();
		} catch (Exception e) {
			log.error("init channellist Error", e);
		}}
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
		PayTJ paytj = new PayTJ();		
		paytj.setYwid(ywid);
		paytj.setChannelid(channelid);
		List<PayTJ> list = new ArrayList<PayTJ>();		
		try {
			
		
			if(flag.equals("1")){
			list = payTJManage.findPayTJList_allGroupByChannel(paytj, beginTime, endTime);
			}else if(flag.equals("2")){
				list = payTJManage.findPayTJList_all(paytj, beginTime, endTime);
			}
			request.setAttribute("flag",flag);
			
			
		} catch (Exception e) {
			log.error("find Error", e);
		}
		
		PayTJ tj = new PayTJ();
		for(int i = 0; i<list.size(); i++){
			PayTJ model = (PayTJ)list.get(i);
			tj.setUsernum(tj.getUsernum()+model.getUsernum());
			tj.setPaymoney(tj.getPaymoney()+model.getPaymoney());
		}
		request.setAttribute("tj", tj);
		request.setAttribute("list", list);

		return new ModelAndView("paytj_all");
	}
	@RequestMapping("/tj/userpaytj.do")
	public ModelAndView findByUser(@RequestParam(value="beginTime",required=false)String beginTime,@RequestParam(value="endTime",required=false)String endTime,HttpServletRequest request) throws Exception {
		User user = (User) request.getSession().getAttribute("user");	
		List<UserCfg> userCfgs = userCfgDAO.findByProperty("user.id", user.getId());
		List<Integer> channels=new ArrayList<Integer>();
		for(UserCfg userCfg:userCfgs){
			channels.add(userCfg.getChannel().getId());
		}
		//处理参数 当参数为空的时候   设置本月第一天   和   最后一天
		if((beginTime==null) || ("".equals(beginTime.trim()))) {
			beginTime = DateUtil.getFirstDayFromMonth();
		}
		if((endTime==null) || ("".equals(endTime.trim()))) {
			endTime = DateUtil.getLastDayFromMonth();
		}
		List<PayTJ> list = payTJDAO.findPayTJList_allGroupByChannels(channels, beginTime, endTime);
		request.setAttribute("list", list);
		//统计总数
		PayTJ tj = new PayTJ();
		tj.setUsernum(0);
		tj.setPaymoney(0);
		for(int i = 0; i<list.size(); i++){
			PayTJ model = (PayTJ)list.get(i);
			if(model!=null){
				tj.setUsernum(tj.getUsernum()+model.getUsernum());
				tj.setPaymoney(tj.getPaymoney()+model.getPaymoney());
			}
		}
		request.setAttribute("tj2", tj);
		return new ModelAndView("paytj_user");
	}	
	
    public void init(HttpServletRequest request){
    	List<Channel> channellist= channelManage.findAll();
    	List<Yw> ywllist = ywManage.findAll();
		request.setAttribute("ywlist", ywllist);
		request.setAttribute("channellist", channellist);
			
	}
}
