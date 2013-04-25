package com.ruicai.basis.hezuo.business.impl;

import java.util.List;

import com.ruicai.basis.entity.TUserInfo;
import com.ruicai.basis.hezuo.business.TUserInfoManage;
import com.ruicai.basis.hezuo.dao.TUserInfoDAO;

public class TUserInfoManageImpl implements TUserInfoManage{

	private TUserInfoDAO tuserInfoDAO;

	public void setTuserInfoDAO(TUserInfoDAO tuserInfoDAO) {
		this.tuserInfoDAO = tuserInfoDAO;
	}

	/**
	 * 查询用户
	 * @param model
	 * @param firsttime
	 * @param lasttime
	 * @return
	 * @throws Exception
	 */
	public List<TUserInfo> findTUserInfoList(String channel,String time) throws Exception{
		return tuserInfoDAO.findTUserInfoList(channel, time);
	}
	
	/**
	 * 查询用户
	 * @param model
	 * @param firsttime
	 * @param lasttime
	 * @return
	 * @throws Exception
	 */
	public List<TUserInfo> findTUserInfoList(Integer beginnum ,Integer endnum) throws Exception {
		return tuserInfoDAO.findTUserInfoList(beginnum, endnum);
	}
		
}
