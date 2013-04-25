package com.ruicai.basis.sys.dao;

import java.util.List;

import com.ruicai.basis.common.RollPage;
import com.ruicai.basis.entity.User;

public interface UserDAO {

	/**
	 * 按字段查询
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List<User> findByProperty(String propertyName, Object value);
	
	/**
	 * 返回所有用户列表
	 * @return
	 * @throws Exception
	 */
	public List findAll() throws Exception ;
	
	/**
	 * 返回用户信息
	 * @interpret ������������ 
	 * @param operator
	 * @return List<Operator>
	 * @throws Exception
	 */
	public List<User> findLoginInfo(User user) throws Exception;
	
	/**
	 * 获得实体
	 * @param id
	 * @return
	 */
	public User findById(java.lang.Integer id);
	
	/**
	 * 返回查询总数
	 * 
	 * @param Role
	 * @return
	 * @throws Exception
	 */
	public Integer findListCount(User instance) throws Exception ;
	
	/**
	 * 返回分页查询结果
	 * @param request
	 * @param Role
	 * @param rollPage
	 * @return
	 */
	public List<User> findList(User instance, RollPage rollPage, Integer resCount) throws Exception ;
	    
	/**
	 * 保存实体
	 * @param transientInstance
	 */
	public void save(User instance);
	
	/**
	 * 更新实体
	 * @param transientInstance
	 */
	public void update(User instance);
		
	/**
	 * 批量删除
	 * @param ids
	 * @throws Exception
	 */
	public void deleteJL(String ids) throws Exception ;
}
