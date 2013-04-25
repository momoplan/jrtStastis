package com.ruicai.basis.userinfo.dao;

import java.util.List;

import com.ruicai.basis.entity.BuyTJ;
import com.ruicai.basis.entity.Ttransaction;
import com.ruicai.basis.entity.UserInfo;
import com.ruicai.basis.entity.UserTransAction;

public interface UserInfoDAO {

	/**
	 * 获取用户信息
	 * @param instance
	 * @return
	 */
	public UserInfo findUserInfo(String tel) throws RuntimeException ;
	
	/**
	 * 返回用户交易信息
	 * @param userno
	 * @return
	 */
	public List<UserTransAction> findUserTransAction(String userno);
	 
	public List findTtrlist(final String hql, List<Object> values,
			 final int offset, final int pageSize);
	
	/**
	 * 返回查询总数
	 * 
	 * @param instance
	 * @return
	 * @throws Exception
	 */
	public Integer findListCount(String hql, List<Object> values) throws Exception;
	
	/**
	 * 返回查询总钱数
	 * @return
	 * @throws Exception
	 */
	public Long findSumAmt(String hql, List<Object> values) throws Exception;
}
