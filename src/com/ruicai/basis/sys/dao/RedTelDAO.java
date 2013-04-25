package com.ruicai.basis.sys.dao;

import java.util.List;

import com.ruicai.basis.common.RollPage;
import com.ruicai.basis.entity.RedTel;

public interface RedTelDAO {

	/**
	 * 获得实体
	 * @param id
	 * @return
	 */
	public RedTel findById(java.lang.Integer id);
	
	/**
	 * 返回查询总数
	 * 
	 * @param Role
	 * @return
	 * @throws Exception
	 */
	public Integer findListCount(RedTel instance) throws Exception ;
	
	/**
	 * 返回分页查询结果
	 * @param request
	 * @param Role
	 * @param rollPage
	 * @return
	 */
	public List<RedTel> findList(RedTel instance, RollPage rollPage, Integer resCount) throws Exception ;
	    
	/**
	 * 保存实体
	 * @param transientInstance
	 */
	public void save(RedTel instance);
	
	/**
	 * 更新实体
	 * @param transientInstance
	 */
	public void update(RedTel instance);
		
	/**
	 * 批量删除
	 * @param ids
	 * @throws Exception
	 */
	public void delete(String ids) throws Exception ;
	
	public List<RedTel> findByProperty(String propertyName, Object value) ;
}
