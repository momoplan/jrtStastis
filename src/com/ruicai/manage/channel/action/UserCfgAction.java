package com.ruicai.manage.channel.action;

import java.io.IOException;
import java.util.ArrayList;
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
import com.ruicai.basis.channel.business.UserCfgManage;
import com.ruicai.basis.common.RollPage;
import com.ruicai.basis.common.TypeChange;
import com.ruicai.basis.entity.Channel;
import com.ruicai.basis.entity.User;
import com.ruicai.basis.entity.UserCfg;
import com.ruicai.basis.sys.business.UserManage;

@Controller
public class UserCfgAction {

	private static Log log = LogFactory.getLog(UserCfgAction.class);

	private String viewPage;
	private String viewPage1;
	private String viewPage2;
	
	@Autowired
	UserCfgManage usercfgManage;
	
	@Autowired
	UserManage userManage ;
	
	@Autowired
	ChannelManage channelManage;

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

	@RequestMapping("/channel/listusercfg.do")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) {

		UserCfg model = new UserCfg();
		User user = new User();
		Channel channel = new Channel();

		Integer userid = TypeChange.objectToInt(request.getParameter("userid"));
		user.setId(userid);
		channel.setId(0);
		model.setUser(user);
		model.setChannel(channel);
		
		request.setAttribute("userid", userid);

		List<User> userlist = new ArrayList<User>();

		try {
			userlist = userManage.findAll();

		} catch (Exception e) {
			log.error("find user list Error", e);
		}

		request.setAttribute("userlist", userlist);

		List<UserCfg> usercfglist = new ArrayList<UserCfg>();

		RollPage rollPage = new RollPage();

		try {
			usercfglist = usercfgManage.findList(request, model, rollPage);

		} catch (Exception e) {
			log.error("find Error", e);
		}

		request.setAttribute("list", usercfglist);

		return new ModelAndView(this.getViewPage());
	}

	@RequestMapping("/channel/newusercfg.do")
	public ModelAndView newusercfg(HttpServletRequest request,HttpServletResponse response) {

		List<User> userlist = new ArrayList<User>();

		try {
			userlist = userManage.findAll();

		} catch (Exception e) {
			log.error("find user list Error", e);
		}

		request.setAttribute("userlist", userlist);
		

        List<Channel> channellist = new ArrayList<Channel>();

		try {
			channellist = channelManage.findAll();			
			
		} catch (Exception e) {
			log.error("newchannel Error", e);
		}	
		
		request.setAttribute("channellist", channellist);

		return new ModelAndView(this.getViewPage1());
	}

	@RequestMapping("/channel/findusercfg.do")
	public ModelAndView find(HttpServletRequest request,
			HttpServletResponse response) {
		
		Integer id = TypeChange.objectToInt(request.getParameter("id"));

		UserCfg model = usercfgManage.findById(id);		
		request.setAttribute("model", model);
		
		List<User> userlist = new ArrayList<User>();

		try {
			userlist = userManage.findAll();

		} catch (Exception e) {
			log.error("find user list Error", e);
		}

		request.setAttribute("userlist", userlist);
		
        List<Channel> channellist = new ArrayList<Channel>();

		try {
			channellist = channelManage.findAll();			
			
		} catch (Exception e) {
			log.error("newchannel Error", e);
		}	
		
		request.setAttribute("channellist", channellist);		
		
		return new ModelAndView(this.getViewPage2());
	}

	@RequestMapping("/channel/saveusercfg.do")
	public void save(HttpServletRequest request, HttpServletResponse response) {
		
		Integer userid = TypeChange.objectToInt(request.getParameter("userid"));
		Integer channelid = TypeChange.objectToInt(request.getParameter("channelid"));
		
		UserCfg usercfg = new UserCfg();
		User user = new User();
		user.setId(userid);
		Channel channel = new Channel();
		channel.setId(channelid);
		
		usercfg.setUser(user);
		usercfg.setChannel(channel);
		
		try {
		usercfgManage.save(usercfg);
		} catch (Exception e) {
			log.error("save Error", e);
		}
		
		try {
			response.sendRedirect("listusercfg.do");
		} catch (IOException e) {
			log.error("sendRedirect Error", e);
		}
	}

	@RequestMapping("/channel/delusercfg.do")
	public void del(HttpServletRequest request,	HttpServletResponse response) {
		
		String ids = request.getParameter("ids");
    	ids = ids.replace("'","");
    	ids = ids.substring(0,ids.lastIndexOf(','));
    	//System.out.println(ids);
    	
    	try {
    		usercfgManage.delete(ids);
		} catch (Exception e) {
			log.error("del Error", e);
		}
    	
		try {
			response.sendRedirect("listusercfg.do");
		} catch (IOException e) {
			log.error("sendRedirect Error", e);
		}
	}

	@RequestMapping("/channel/updateusercfg.do")
	public void update(HttpServletRequest request, HttpServletResponse response) {
		
    	Integer id = TypeChange.objectToInt(request.getParameter("id"));
    	Integer userid = TypeChange.objectToInt(request.getParameter("userid"));
		Integer channelid = TypeChange.objectToInt(request.getParameter("channelid"));

		try {
			UserCfg model = usercfgManage.findById(id);
			
			User user = model.getUser();
			Channel channel = model.getChannel();			
			user.setId(userid);
			channel.setId(channelid);
			model.setUser(user);
			model.setChannel(channel);

			usercfgManage.update(model);
		} catch (Exception e) {
			log.error("update Error", e);
		}
		
		try {
			response.sendRedirect("listusercfg.do");
		} catch (IOException e) {
			log.error("sendRedirect Error", e);
		}
	}
}
