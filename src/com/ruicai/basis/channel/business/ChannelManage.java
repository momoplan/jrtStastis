package com.ruicai.basis.channel.business;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ruicai.basis.common.RollPage;
import com.ruicai.basis.entity.Channel;

public interface ChannelManage {
  
	/**
	 * 返回查询总数
	 * 
	 * @param Channel
	 * @return
	 * @throws Exception
	 */
	public Integer findListCount(Channel instance) throws Exception ;
	
	/**
	 * 返回分页查询结果
	 * @param request
	 * @param menu
	 * @param rollPage
	 * @return
	 */
	public List<Channel> findList(HttpServletRequest request, Channel instance, RollPage rollPage) throws Exception;
	
	/**
     * 返回实体
     * @param id
     * @return
     */
    public Channel findById(java.lang.Integer id) ;
    
	/**
	 * 保存实体
	 * @param transientInstance
	 */
	public void save(Channel instance);
	
	/**
	 * 更新实体
	 * @param transientInstance
	 */
	public void update(Channel instance);
		
	/**
	 * 批量删除
	 * @param ids
	 * @throws Exception
	 */
	public void delete(String ids) throws Exception ;
	
	/**
	 * 返回列表
	 * @return
	 */
	public List<Channel> findAll() ;
	
	/**
	 * 返回实体
	 * @param instance
	 * @return
	 */
	public Channel findByExample(Channel instance);
	
	/**
	 * 添加合作方式
	 * @param instance
	 * @return
	 */
	public int createCooperat(HashMap hs);
	/**
	 * 删除合作方式
	 * @param instance
	 * @return
	 */
	public int delCooperat(String code);
	/**
	 * 通过ChannelId查询合作方式
	 * @param instance
	 * @return
	 */
	public List findCooperatByChannelId(HashMap hs);
	/**
	 * 查询渠道
	 * @param instance
	 * @return
	 */
	public List<Channel> findByCode(Channel instance);
	
	
	/**
	 * 合作方式列表
	   @author       丁俊杰
	   @createDate   2010-11-9
	   @param request
	   @throws Exception
	 */
	public void cooperatList(HttpServletRequest request)throws Exception;
	
}
