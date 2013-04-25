package com.ruicai.basis.yw.dao;

import java.util.List;

import com.ruicai.basis.common.RollPage;
import com.ruicai.basis.entity.Yw;

public interface YwDAO {	
	
	/**
	 * 返回查询总数
	 * 
	 * @param yw
	 * @return
	 * @throws Exception
	 */
	public Integer findListCount(Yw instance) throws Exception ;
	
	/**
	 * 返回分页查询结果
	 * @param request
	 * @param menu
	 * @param rollPage
	 * @return
	 */
	public List<Yw> findList(Yw instance, RollPage rollPage, Integer resCount) throws Exception ;
	
	/**
     * 返回实体
     * @param id
     * @return
     */
    public Yw findById(java.lang.Integer id) ;
    
	/**
	 * 保存实体
	 * @param transientInstance
	 */
	public void save(Yw instance);
	
	/**
	 * 更新实体
	 * @param transientInstance
	 */
	public void update(Yw instance);
		
	/**
	 * 批量删除
	 * @param ids
	 * @throws Exception
	 */
	public void delete(String ids) throws Exception ;
	
	/**
	 * 级联删除
	 * @param ids
	 * @throws Exception
	 */
	public void deleteJL(String ids) throws Exception ;
	
	/**
	 * 返回列表
	 * @return
	 */
	public List<Yw> findAll() ;
	
	/**
	 * 返回实体
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List findByProperty(String propertyName, Object value) ;
}
