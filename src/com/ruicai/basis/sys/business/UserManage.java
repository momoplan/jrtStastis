package com.ruicai.basis.sys.business;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ruicai.basis.common.RollPage;
import com.ruicai.basis.entity.User;

public interface UserManage {

	/**
	 * 返回所有用户列表
	 * @return
	 * @throws Exception
	 */
	public List<User> findAll() throws Exception ;
	
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
	
	/**
	 * 用户登录验证
	 * @author liukw
	 * @return boolean
	 */
	public boolean findLoginInfo(User user,HttpServletRequest request)throws Exception;
	
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
	public List<User> findList(HttpServletRequest request, User instance, RollPage rollPage) throws Exception ;
	
	/**
	 * 判断用户名是否重复
	 * @param username
	 * @return
	 */
	public boolean isSingleName(String username);
    
}
