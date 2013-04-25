package com.ruicai.basis.tj.dao;

import java.util.List;

import com.ruicai.basis.entity.BuyTJ;
import com.ruicai.basis.entity.UserActionTJ;

public interface UserActionTJDAO {

	/**
	 * 返回用户行为统计结果
	 * @param userActionTJ
	 * @param firsttime
	 * @param lasttime
	 * @return
	 * @throws Exception
	 */
	public List<UserActionTJ> findUserActionTJList(UserActionTJ userActionTJ,String firsttime,String lasttime) throws Exception; 
	public List findUserActionTJ(String firsttime,String lasttime,String channel) throws Exception; 
	public boolean findChannelByCode(String channel) throws Exception; 
}
