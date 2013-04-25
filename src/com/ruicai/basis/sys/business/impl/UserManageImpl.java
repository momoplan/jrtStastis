package com.ruicai.basis.sys.business.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ruicai.basis.common.ConvertLang;
import com.ruicai.basis.common.RollPage;
import com.ruicai.basis.entity.User;
import com.ruicai.basis.sys.business.UserManage;
import com.ruicai.basis.sys.dao.UserDAO;

public class UserManageImpl implements UserManage{

	private UserDAO userDAO;
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	/**
	 * 返回所有用户列表
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<User> findAll() throws Exception {
		return userDAO.findAll();
	}
	
	/**
	 * 用户登录验证
	 * @author liukw
	 * @return boolean
	 */
	@Override
	public boolean findLoginInfo(User user,HttpServletRequest request)throws Exception{
        
		boolean flag = false;
		
		User user_ = null;

		List<User> list = new ArrayList<User>();

		if(user!=null){
			
			list = userDAO.findLoginInfo(user);

			if(list!=null){
				if(list.size()<=0||list.size()>1){
					flag = false;
				}
				
				if(list.size()==1){
					user_ = list.get(0);
					request.getSession().setAttribute("user", user_);				
					flag = true;
				}
			}
		}
		
		return flag;
	}
	
	/**
	 * 获得实体
	 * @param id
	 * @return
	 */
	@Override
	public User findById(java.lang.Integer id){
		return userDAO.findById(id);
	}
	
	/**
	 * 返回查询总数
	 * 
	 * @param Role
	 * @return
	 * @throws Exception
	 */
	@Override
	public Integer findListCount(User instance) throws Exception {
        Integer resCount = 0;
		
		if(instance!=null){
			//���������������������������������
			resCount = userDAO.findListCount(instance);
		}		
		return resCount;
	}
	
	/**
	 * 返回分页查询结果
	 * @param request
	 * @param Role
	 * @param rollPage
	 * @return
	 */
	@Override
	public List<User> findList(HttpServletRequest request, User instance, RollPage rollPage) throws Exception {
        Integer resCount = 0;
		
		List<User> list = new ArrayList<User>();
		
		if(instance!=null){
			
			String page = request.getParameter("page");
			rollPage = new RollPage(page == null || page.equals("") ? 0: ConvertLang.convertint(page));
			rollPage.setPagePer(10);
			resCount = findListCount(instance);
			
			int rescount = resCount.intValue()/rollPage.getPagePer();
			
			if(rollPage.getPageCur()>rescount){
				rollPage.setPageCur(0);
			}
			
			list = userDAO.findList(instance, rollPage, resCount);

			request.setAttribute("rollPage", rollPage);
		}

		return list;
	}
    
	/**
	 * 保存实体
	 * @param transientInstance
	 */
	@Override
	public void save(User instance){
		userDAO.save(instance);
	}
	
	/**
	 * 更新实体
	 * @param transientInstance
	 */
	@Override
	public void update(User instance){
		userDAO.update(instance);
	}
		
	/**
	 * 批量删除
	 * @param ids
	 * @throws Exception
	 */
	@Override
	public void deleteJL(String ids) throws Exception {
		userDAO.deleteJL(ids);
	}	
	
	/**
	 * 判断用户名是否重复
	 * @param username
	 * @return
	 */
	@Override
	public boolean isSingleName(String username){
		List<User> list = userDAO.findByProperty("name", username.trim());
		if(list.size()==0){
			return true;
		}else{
			return false;
		}
	}
}
