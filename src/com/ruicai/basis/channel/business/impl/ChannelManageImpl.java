package com.ruicai.basis.channel.business.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ruicai.basis.channel.business.ChannelManage;
import com.ruicai.basis.channel.dao.ChannelDAO;
import com.ruicai.basis.common.ConvertLang;
import com.ruicai.basis.common.RollPage;
import com.ruicai.basis.common.util.CommonUtil;
import com.ruicai.basis.common.util.FinalVar;
import com.ruicai.basis.entity.Channel;
import com.ruicai.basis.entity.TCooperat;
import com.ruicai.manage.channel.action.ChannelAction;

public class ChannelManageImpl implements ChannelManage{

	private ChannelDAO channelDAO;
	private static Log log = LogFactory.getLog(ChannelManageImpl.class);
	public void setChannelDAO(ChannelDAO channelDAO) {
		this.channelDAO = channelDAO;
	}

	/**
	 * 返回查询总数
	 * 
	 * @param channel
	 * @return
	 * @throws Exception
	 */
	public Integer findListCount(Channel instance) throws Exception {
        Integer resCount = 0;
		
		if(instance!=null){
			//���������������������������������
			resCount = channelDAO.findListCount(instance);
		}		
		return resCount;
	}
	
	/**
	 * 返回分页查询结果
	 * @param request
	 * @param Channel
	 * @param rollPage
	 * @return
	 */
	public List<Channel> findList(HttpServletRequest request, Channel instance, RollPage rollPage) throws Exception{
        Integer resCount = 0;
		
		List<Channel> list = new ArrayList<Channel>();
		
		if(instance!=null){
			
			String page = request.getParameter("page");
			rollPage = new RollPage(page == null || page.equals("") ? 0: ConvertLang.convertint(page));
			rollPage.setPagePer(10);
			resCount = findListCount(instance);

			int rescount = resCount.intValue()/rollPage.getPagePer();
			
			if(rollPage.getPageCur()>rescount){
				rollPage.setPageCur(0);
			}
			
			
			
			/**
			 * 将获取的值置入实体中
			 */
			
			if (CommonUtil.checkIsEmpty(instance.getYw().getStartTime()) == true
					&& CommonUtil.checkIsEmpty(instance.getYw().getEndTime()) == true) {
				// 计算开始与截止的天数
				instance.setDays(CommonUtil.getIntervalDays(instance.getYw()
						.getStartTime(), instance.getYw().getEndTime(),
						FinalVar.FORMAT_STRING));
				/**
				 * 计算开始与截止的月份数
				 */
				instance.setMonths(CommonUtil.dispersionMonth2(instance.getYw().getStartTime(), instance.getYw().getEndTime()));
			
			}
			    log.info("=================instance为月份赋值"+instance.getMonths());
			    
			Long startTime = System.currentTimeMillis();
			list = channelDAO.findList(instance, rollPage, resCount);
            log.info("====================渠道列表的执行时间"+(System.currentTimeMillis()-startTime)+"毫秒");
			request.setAttribute("rollPage", rollPage);
		}

		return list;
	}	
	
	/**
     * 返回实体
     * @param id
     * @return
     */
    public Channel findById(Integer id) {
    	return channelDAO.findById(id);
    }
    
	/**
	 * 保存实体
	 * @param transientInstance
	 */
	public void save(Channel instance){
		channelDAO.save(instance);
	}
	
	/**
	 * 更新实体
	 * @param transientInstance
	 */
	public void update(Channel instance){
		channelDAO.update(instance);
	}
		
	/**
	 * 批量删除
	 * @param ids
	 * @throws Exception
	 */
	public void delete(String ids) throws Exception {
		channelDAO.delete(ids);
	}
	
	/**
	 * 返回列表
	 * @return
	 */
	public List<Channel> findAll() {
		return channelDAO.findAll();
	}
	
	/**
	 * 返回实体
	 */
	public Channel findByExample(Channel instance) {
		List list = channelDAO.findByCode(instance);
		Channel model = new Channel();
		model.setName(instance.getCode());
		if(list.size()>0)
			model = (Channel)list.get(0);
		return model;
	}

	/**
	 * 添加合作方式
	 * @param instance
	 * @return
	 */
	public int createCooperat(HashMap hs) {
		
		
		int count=0;
		try {
			count = channelDAO.createCooperat(hs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * 查询合作方式
	 * @param instance
	 * @return
	 */
	public List findCooperatByChannelId(HashMap hs) {
		return channelDAO.findCooperatByChannelId(hs);
	}

	/**
	 * 删除合作方式
	 * @param instance
	 * @return
	 */
	public int delCooperat(String code) {
		try {
			return channelDAO.delCooperat(code);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 查询渠道
	 * @param instance
	 * @return
	 */
	public List<Channel> findByCode(Channel instance) {
		// TODO Auto-generated method stub
		return channelDAO.findByCode(instance);
	}

	/**
	 * 合作方式列表
	   @author       丁俊杰
	   @createDate   2010-11-9
	   @param request
	   @throws Exception
	 */
	public void cooperatList(HttpServletRequest request) throws Exception {
		 String code = request.getParameter("code");//渠道编号
         String visitnum = request.getParameter("visitnum");//访问量
         String regnum = request.getParameter("regnum");//注册用户数
         String days = request.getParameter("days"); //天数
 		 String balance  = request.getParameter("balance");//结算金额
 		 String buymoney = request.getParameter("buymoney");//购彩金额
 		 /**
 		  * 相差的月份开始结束日期
 		  */
 		 int months = CommonUtil.dispersionMonth2(request.getParameter("startTime"), request.getParameter("endTime")) ;
         
 		 Channel channel = new Channel();
         channel.setCode(code);
         List<TCooperat> tcooperatList = new ArrayList<TCooperat>();
         List<TCooperat> list =  channelDAO.findAllTCooperat(channel);
         if(list != null&&list.size()>0){
	         for (int i = 0; i < list.size(); i++) {
				TCooperat cooperat = list.get(i);
				// 如果是CPA regnum*count 注册用户数*单个金额
				if (cooperat.getCooperatType().equals(FinalVar.COOPERAT_TYPE_CPA))
				cooperat.setCpaCount(cooperat.getCount()*CommonUtil.strToLong(regnum));
				// 如果是cpc visitnum* count 访问量*金额
				if (cooperat.getCooperatType().equals(FinalVar.COOPERAT_TYPE_CPC))
				cooperat.setCpcCount(cooperat.getCount()*CommonUtil.strToLong(visitnum));
				// 如果是cps buymoney*count/100 购彩金额*金额 除以100
				
				if (cooperat.getCooperatType().equals(FinalVar.COOPERAT_TYPE_CPS))
				cooperat.setCpsCount((cooperat.getCount()/100)*CommonUtil.strToDouble(buymoney));
				// 如果是固定金额(月) count/30*days 金额除以30 乘以天数
				//改为计算月份
				if (cooperat.getCooperatType().equals(FinalVar.COOPERAT_TYPE_RIVET))
				   cooperat.setRivetCount((months==0?1:months)*(cooperat.getCount()));
				   tcooperatList.add(cooperat);
				log.info("=================months*(cooperat.getCount())"+months*(cooperat.getCount()));
			}
       }
         log.info("============list=========="+list.size());
         request.setAttribute("list", list);   
         request.setAttribute("balance", balance);   
         
	}
	
}
