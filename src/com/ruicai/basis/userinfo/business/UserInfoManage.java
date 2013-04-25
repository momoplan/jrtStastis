package com.ruicai.basis.userinfo.business;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ruicai.basis.common.RollPage;
import com.ruicai.basis.entity.Ttransaction;
import com.ruicai.basis.entity.User;
import com.ruicai.basis.entity.UserInfo;
import com.ruicai.basis.entity.UserTransAction;

public interface UserInfoManage {
	 
	/**
	 * 获取用户信息
	 * @param instance
	 * @return
	 */
	public UserInfo findUserInfo(String tel) throws RuntimeException;
	
	/**
	 * 返回用户交易信息
	 * @param userno
	 * @return
	 */
	public List<UserTransAction> findUserTransAction(String userno);
	
	/**
	 * 返回查询总数
	 * 
	 * @param Role
	 * @return
	 * @throws Exception
	 */
	public Integer findListCount() throws Exception ;
	
	/**
	 * 返回分页查询结果
	 * @param request
	 * @param Role
	 * @param rollPage
	 * @return
	 */
	public List<Ttransaction> findList(HttpServletRequest request, RollPage rollPage) throws Exception ;

}
