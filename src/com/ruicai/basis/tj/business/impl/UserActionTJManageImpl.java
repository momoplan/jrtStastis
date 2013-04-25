package com.ruicai.basis.tj.business.impl;

import java.util.List;

import com.ruicai.basis.entity.UserActionTJ;
import com.ruicai.basis.tj.business.UserActionTJManage;
import com.ruicai.basis.tj.dao.UserActionTJDAO;

public class UserActionTJManageImpl implements UserActionTJManage{

	private UserActionTJDAO userActionTJDAO;

	public void setUserActionTJDAO(UserActionTJDAO userActionTJDAO) {
		this.userActionTJDAO = userActionTJDAO;
	}

	/**
	 * 返回用户行为统计结果
	 * @param userActionTJ
	 * @param firsttime
	 * @param lasttime
	 * @return
	 * @throws Exception
	 */
	public List<UserActionTJ> findUserActionTJList(UserActionTJ userActionTJ,String firsttime,String lasttime) throws Exception{
		return userActionTJDAO.findUserActionTJList(userActionTJ, firsttime, lasttime);
	}

	public List findUserActionTJ(String firsttime, String lasttime,String channel)
			throws Exception {
		return userActionTJDAO.findUserActionTJ(firsttime, lasttime,channel);
	}

	@Override
	public boolean findChannelByCode(String channel) throws Exception {
		return userActionTJDAO.findChannelByCode(channel);
	}

}
