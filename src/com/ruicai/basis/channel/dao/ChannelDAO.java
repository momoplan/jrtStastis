package com.ruicai.basis.channel.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruicai.basis.common.RollPage;
import com.ruicai.basis.entity.Channel;
import com.ruicai.basis.entity.TCooperat;

public interface ChannelDAO {	
	
	/**
	 * 返回查询总数
	 * 
	 * @param yw
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
	public List<Channel> findList(Channel instance, RollPage rollPage, Integer resCount) throws Exception ;
	
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

	/**
	 * 添加合作方式
	 * @param instance
	 * @return
	 */
	public int createCooperat(HashMap hs)throws Exception;
	/**
	 * 删除合作方式
	 * @param instance
	 * @return
	 */
	public int delCooperat(String code)throws Exception;
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
 * 查询所有合作方式
   @author       丁俊杰
   @createDate   2010-11-9
   @param channel
   @return
   @throws Exception
 */
public List<TCooperat> findAllTCooperat(Channel channel) throws Exception;
	/**
	 * 查询某个业务对应的所有渠道
	 * @param instance
	 * @return
	 */
	public Map<String, Channel> findByYwid(String ywid);
}
