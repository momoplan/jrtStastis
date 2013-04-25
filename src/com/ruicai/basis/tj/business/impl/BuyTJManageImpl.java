package com.ruicai.basis.tj.business.impl;

import java.util.List;

import com.ruicai.basis.entity.BuyTJ;
import com.ruicai.basis.entity.User;
import com.ruicai.basis.tj.business.BuyTJManage;
import com.ruicai.basis.tj.dao.BuyTJDAO;

public class BuyTJManageImpl implements BuyTJManage{

	private BuyTJDAO buyTJDAO;	

	public void setBuyTJDAO(BuyTJDAO buyTJDAO) {
		this.buyTJDAO = buyTJDAO;
	}

	/**
	 * 返回购彩方式统计结果
	 * @param BuyTJ
	 * @param firsttime
	 * @param lasttime
	 * @return
	 */
	public List<BuyTJ> findBuyTJList_all(BuyTJ buyTJ,String firsttime,String lasttime) throws Exception{
		return buyTJDAO.findBuyTJList_all(buyTJ, firsttime, lasttime);
	}
	
	/**
	 * 返回合作方购彩方式统计结果
	 * @param user
	 * @param firsttime
	 * @param lasttime
	 * @return
	 * @throws Exception
	 */
	public List<BuyTJ> findBuyTJList_hezuo(User user, String firsttime, String lasttime) throws Exception{
		return buyTJDAO.findBuyTJList_hezuo(user, firsttime, lasttime);
	}

}
