package com.ruicai.basis.hezuo.dao;

import java.util.List;

import com.ruicai.basis.entity.TUserInfo;

public interface TUserInfoDAO {	
    
	/**
	 * 查询用户
	 * @param model
	 * @param firsttime
	 * @param lasttime
	 * @return
	 * @throws Exception
	 */
	public List<TUserInfo> findTUserInfoList(String channel,String time) throws Exception ;
	
	
	/**
	 * 查询用户
	 * @param model
	 * @param firsttime
	 * @param lasttime
	 * @return
	 * @throws Exception
	 */
	public List<TUserInfo> findTUserInfoList(Integer beginnum ,Integer endnum) throws Exception ;
}
