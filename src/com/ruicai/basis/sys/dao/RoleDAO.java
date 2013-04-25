package com.ruicai.basis.sys.dao;

import java.util.List;

import com.ruicai.basis.common.RollPage;
import com.ruicai.basis.entity.Role;
import com.ruicai.basis.entity.Yw;

public interface RoleDAO {

	/**
	 * 返回查询总数
	 * 
	 * @param Role
	 * @return
	 * @throws Exception
	 */
	public Integer findListCount(Role instance) throws Exception ;
	
	/**
	 * 返回分页查询结果
	 * @param request
	 * @param Role
	 * @param rollPage
	 * @return
	 */
	public List<Role> findList(Role instance, RollPage rollPage, Integer resCount) throws Exception ;
	
	/**
     * 返回实体
     * @param id
     * @return
     */
    public Role findById(java.lang.Integer id) ;
    
	/**
	 * 保存实体
	 * @param transientInstance
	 */
	public void save(Role instance);
	
	/**
	 * 更新实体
	 * @param transientInstance
	 */
	public void update(Role instance);
		
	/**
	 * 批量删除
	 * @param ids
	 * @throws Exception
	 */
	public void deleteJL(String ids) throws Exception ;
	
	/**
	 * 返回列表
	 * @return
	 */
	public List<Role> findAll() ;
		
}
