package com.ruicai.basis.tj.business.impl;

import java.util.List;

import com.ruicai.basis.entity.UserAnalysety;
import com.ruicai.basis.tj.business.UserAnalysetyManage;
import com.ruicai.basis.tj.dao.UserAnalysetyDAO;

public class UserAnalysetyManageImpl implements UserAnalysetyManage{

	private UserAnalysetyDAO userAnalysetyDAO;	

	public void setUserAnalysetyDAO(UserAnalysetyDAO userAnalysetyDAO) {
		this.userAnalysetyDAO = userAnalysetyDAO;
	}

	/**
	 * 返回用户分析统计结果
	 * @param userAnalysety
	 * @param firsttime
	 * @param lasttime
	 * @return
	 * @throws Exception
	 */
	public List<UserAnalysety> findUserAnalysetyList_all(UserAnalysety userAnalysety,String firsttime,String lasttime) throws Exception{
		
		return userAnalysetyDAO.findUserAnalysetyList_all(userAnalysety, firsttime, lasttime);
	}

}
