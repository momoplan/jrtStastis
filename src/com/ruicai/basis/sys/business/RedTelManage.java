package com.ruicai.basis.sys.business;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ruicai.basis.common.RollPage;
import com.ruicai.basis.entity.RedTel;

public interface RedTelManage {

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
	public List<RedTel> findList(HttpServletRequest request, RedTel instance, RollPage rollPage) throws Exception ;
	
	/**
	 * 判断用户名是否重复
	 * @param username
	 * @return
	 */
	public boolean isSingleTel(String tel);
    
}
