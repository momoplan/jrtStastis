package com.ruicai.manage.tj.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruicai.basis.channel.dao.ChannelDAO;
import com.ruicai.basis.channel.dao.UserCfgDAO;
import com.ruicai.basis.entity.Channel;
import com.ruicai.basis.entity.ClientZjlTJ;
import com.ruicai.basis.entity.User;
import com.ruicai.basis.entity.UserCfg;
import com.ruicai.basis.tj.dao.ClientZjlTJDAO;
import com.ruicai.basis.util.DateUtil;

@Controller
public class ClientTJAction{

	@Autowired
	UserCfgDAO userCfgDAO;
	@Autowired
	ClientZjlTJDAO zjlTJDAO;
	@Autowired
	ChannelDAO channelDAO;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/tj/zjltj.do")
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
		/*
		List<ClientZjlTJ> list = zjlTJDAO.findZjlByChannels(channels, beginTime, endTime);
		//统计总数
		ClientZjlTJ tj = new ClientZjlTJ();
		tj.setZjl(0);
		Map<String, Channel> channelsMap = channelDAO.findByYwid("24");
		// 统计每个渠道的总数  并将每个渠道
		for(int i = 0; i<list.size(); i++){
			ClientZjlTJ model = (ClientZjlTJ)list.get(i);
			if(model!=null){
				tj.setZjl(tj.getZjl()+model.getZjl());
				list.get(i).setCoopid(channelsMap.get(model.getCoopid()).getName());
			}
		}
		request.setAttribute("tj2", tj);
		request.setAttribute("list", list);
		return new ModelAndView("zjl_user");
		*/
		//统计总的装机数
		int tjZjl = 0;
		// 统计的渠道要属于"android客户端"这个业务(ywid=24)------>用这个map来将渠道的id转换成name
		Map<String, Channel> channelsMap = channelDAO.findByYwid("24");
		// 以渠道为单位,将每个渠道的装机量放入到一个list的一个元素中去,      每个渠道的装机量也是一个list,以"各天"为单位, list的每个元素里存放的是"这个渠道各天的装机量"
		List<ClientZjlTJ> list = zjlTJDAO.findZjlByChannels(channels, beginTime, endTime);
		Map<String, List<ClientZjlTJ>> channelsListMap = new HashMap<String, List<ClientZjlTJ>>();
		// 统计每个渠道的总数  并将每个渠道按照渠道id添加到对应的'渠道list'中去
		for(int i = 0; i<list.size(); i++){
			ClientZjlTJ model = (ClientZjlTJ)list.get(i);
			// 将渠道的id置换成name
			String coopName = channelsMap.get(model.getCoopid()).getName();
			model.setCoopid(coopName);
			// 若已经有这个渠道了, 则将这个渠道的"装机量对象"添加到"该渠道对应的list"中去,    然后更新到map中
			if(channelsListMap.containsKey(coopName)) {
				List<ClientZjlTJ> oldList = channelsListMap.get(coopName);
				oldList.add(model);
				// 将"该渠道对应的每日装机量list" 更新到map中去
				channelsListMap.put(coopName, oldList);
			} else {
				// 若没有这个渠道, 则新建"该渠道对应的每日装机量list",                      然后添加到map中
				List<ClientZjlTJ> newList = new ArrayList<ClientZjlTJ>();
				newList.add(model);
				// 将"该渠道对应的每日装机量list" 添加到map中去
				channelsListMap.put(coopName, newList);
			}
			// 处理合值
			if(model!=null){
				tjZjl += model.getZjl();
			}
		}
		request.setAttribute("tjZjl", tjZjl);
		request.setAttribute("channelsListMap", channelsListMap);
		return new ModelAndView("zjl_user");
	}	
	
    public void init(HttpServletRequest request){
		request.setAttribute("channelsMap", channelDAO.findByYwid("24"));
	}
}
