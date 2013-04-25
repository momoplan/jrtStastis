package com.ruicai.basis.tj.dao;

import java.util.List;

import com.ruicai.basis.entity.BuyTJ;
import com.ruicai.basis.entity.User;

public interface BuyTJDAO {

	/**
	 * 返回购彩方式统计结果
	 * @param BuyTJ
	 * @param firsttime
	 * @param lasttime
	 * @return
	 */
	public List<BuyTJ> findBuyTJList_all(BuyTJ buyTJ,String firsttime,String lasttime) throws Exception; 
	
	/**
	 * 返回合作方购彩方式统计结果
	 * @param user
	 * @param firsttime
	 * @param lasttime
	 * @return
	 * @throws Exception
	 */
	public List<BuyTJ> findBuyTJList_hezuo(User user, String firsttime, String lasttime) throws Exception;
}
