package com.ruicai.basis.pv.business.impl;

import java.util.List;

import com.ruicai.basis.entity.UserLog;
import com.ruicai.basis.pv.business.UserLogManage;
import com.ruicai.basis.pv.dao.UserLogDAO;

public class UserLogManageImpl implements UserLogManage{

	private UserLogDAO userLogDAO;

	public void setUserLogDAO(UserLogDAO userLogDAO) {
		this.userLogDAO = userLogDAO;
	}

	/**
     * 保存实体
     * @param transientInstance
     */
    public void save(UserLog userlog) {
    	userLogDAO.save(userlog);
    }
		
    /**
	 * 返回最近200条记录
	 * @return
	 * @throws Exception
	 */
	public List<UserLog> findLast200List() throws Exception {
		return userLogDAO.findLast200List();
	}
	
	/**
	 * 返回用户日志信息
	 * @param tel
	 * @return
	 * @throws Exception
	 */
	public List<UserLog> findLogInfo(String tel) throws Exception {
		return userLogDAO.findLogInfo(tel);
	}
}
