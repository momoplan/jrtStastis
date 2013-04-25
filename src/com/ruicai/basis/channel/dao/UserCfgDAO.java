package com.ruicai.basis.channel.dao;

import java.util.List;

import com.ruicai.basis.common.RollPage;
import com.ruicai.basis.entity.UserCfg;

public interface UserCfgDAO {	
	
	/**
	 * 返回查询总数
	 * 
	 * @param yw
	 * @return
	 * @throws Exception
	 */
	public Integer findListCount(UserCfg instance) throws Exception ;
	
	/**
	 * 返回分页查询结果
	 * @param request
	 * @param menu
	 * @param rollPage
	 * @return
	 */
	public List<UserCfg> findList(UserCfg instance, RollPage rollPage, Integer resCount) throws Exception ;
	
	/**
     * 返回实体
     * @param id
     * @return
     */
    public UserCfg findById(java.lang.Integer id) ;
    

    public List findByProperty(String propertyName, Object value) ;
    
	/**
	 * 保存实体
	 * @param transientInstance
	 */
	public void save(UserCfg instance);
	
	/**
	 * 更新实体
	 * @param transientInstance
	 */
	public void update(UserCfg instance);
		
	/**
	 * 批量删除
	 * @param ids
	 * @throws Exception
	 */
	public void delete(String ids) throws Exception ;	
	
}
