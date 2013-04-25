package com.ruicai.basis.userinfo.business.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ruicai.basis.common.ConvertLang;
import com.ruicai.basis.common.RollPage;
import com.ruicai.basis.entity.Ttransaction;
import com.ruicai.basis.entity.User;
import com.ruicai.basis.entity.UserInfo;
import com.ruicai.basis.entity.UserTransAction;
import com.ruicai.basis.userinfo.business.UserInfoManage;
import com.ruicai.basis.userinfo.dao.UserInfoDAO;

public class UserInfoManageImpl implements UserInfoManage{

	private UserInfoDAO userInfoDAO;

	public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
		this.userInfoDAO = userInfoDAO;
	}

	/**
	 * 获取用户信息
	 * @param instance
	 * @return
	 */
	public UserInfo findUserInfo(String tel) throws RuntimeException {
		return userInfoDAO.findUserInfo(tel);
	}
	
	
	/**
	 * 返回用户交易信息
	 * @param userno
	 * @return
	 */
	public List<UserTransAction> findUserTransAction(String userno){
		return userInfoDAO.findUserTransAction(userno);
	}

	@Override
	public Integer findListCount() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ttransaction> findList(HttpServletRequest request,
			RollPage rollPage) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
/*	*//**
	 * 返回查询总数
	 * 
	 * @param Role
	 * @return
	 * @throws Exception
	 *//*
	@Override
	public Integer findListCount() throws Exception {
        Integer resCount = userInfoDAO.findListCount();
		return resCount;
	}
	
	*//**
	 * 返回分页查询结果
	 * @param request
	 * @param Role
	 * @param rollPage
	 * @return
	 *//*
	@Override
	public List<Ttransaction> findList(HttpServletRequest request,  RollPage rollPage) throws Exception {
        Integer resCount = 0;
		
		List<Ttransaction> list = new ArrayList<Ttransaction>();
		resCount = findListCount();
		
		int rescount = resCount.intValue()/rollPage.getPagePer();
		
		if(rollPage.getPageCur()>rescount){
			rollPage.setPageCur(0);
		}
		
		list = userInfoDAO.findList(rollPage, resCount);
		request.setAttribute("rollPage", rollPage);
		return list;
	}*/
}
