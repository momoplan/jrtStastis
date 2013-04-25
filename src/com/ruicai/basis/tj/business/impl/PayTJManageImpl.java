package com.ruicai.basis.tj.business.impl;

import java.util.List;

import com.ruicai.basis.entity.PayTJ;
import com.ruicai.basis.tj.business.PayTJManage;
import com.ruicai.basis.tj.dao.PayTJDAO;

public class PayTJManageImpl implements PayTJManage{

	private PayTJDAO payTJDAO;
	
	public void setPayTJDAO(PayTJDAO payTJDAO) {
		this.payTJDAO = payTJDAO;
	}

	/**
	 * 返回充值方式统计结果
	 * @param pvTJ
	 * @param firsttime
	 * @param lasttime
	 * @return
	 */
	public List<PayTJ> findPayTJList_all(PayTJ payTJ,String firsttime,String lasttime) throws Exception{
		return payTJDAO.findPayTJList_all(payTJ, firsttime, lasttime);
	}

	@Override
	public List<PayTJ> findPayTJList_allGroupByChannel(PayTJ payTJ,
			String firsttime, String lasttime) throws Exception {
		// TODO Auto-generated method stub
		return payTJDAO.findPayTJList_allGroupByChannel(payTJ, firsttime, lasttime);
	}

	@Override
	public List findFailedPayTJList_all(String firsttime, String lasttime)
			throws Exception {
		return payTJDAO.findFailedPayTJList_all(firsttime, lasttime);
	}

}
