package com.ruicai.basis.tj.business.impl;

import java.util.List;

import com.ruicai.basis.entity.PvTJ;
import com.ruicai.basis.entity.User;
import com.ruicai.basis.tj.business.PvTJManage;
import com.ruicai.basis.tj.dao.PvTJDAO;

public class PvTJManageImpl implements PvTJManage{

	private PvTJDAO pvTJDAO;

	public void setPvTJDAO(PvTJDAO pvTJDAO) {
		this.pvTJDAO = pvTJDAO;
	}
	
	/**
	 * 返回PV统计结果
	 * @param pvTJ
	 * @param firsttime
	 * @param lasttime
	 * @return
	 */
	public List<PvTJ> findPvTJList_all(PvTJ pvTJ,String firsttime,String lasttime,String orderLine,String orderRule) throws Exception{
		return pvTJDAO.findPvTJList_all(pvTJ, firsttime, lasttime, orderLine, orderRule);
	}

	public List<PvTJ> findPvTJList_hezuo(User user, String firsttime, String lasttime) throws Exception {
		return pvTJDAO.findPvTJList_hezuo(user, firsttime, lasttime);
	}
}
