package com.ruicai.basis.channel.business;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ruicai.basis.common.RollPage;
import com.ruicai.basis.entity.UserCfg;

public interface UserCfgManage {

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
	 * @param Role
	 * @param rollPage
	 * @return
	 */
	public List<UserCfg> findList(HttpServletRequest request, UserCfg instance, RollPage rollPage) throws Exception ;
	
	/**
     * 返回实体
     * @param id
     * @return
     */
    public UserCfg findById(java.lang.Integer id) ;
    
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
