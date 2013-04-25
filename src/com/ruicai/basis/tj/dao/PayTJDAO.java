package com.ruicai.basis.tj.dao;

import java.util.Date;
import java.util.List;

import com.ruicai.basis.entity.PayTJ;

public interface PayTJDAO {

	/**
	 * 返回充值方式统计结果
	 * @param PayTJ
	 * @param firsttime
	 * @param lasttime
	 * @return
	 */
	public List<PayTJ> findPayTJList_all(PayTJ payTJ,String firsttime,String lasttime) throws Exception; 
	public List<PayTJ> findPayTJList_allGroupByChannel(PayTJ payTJ,String firsttime,String lasttime) throws Exception;
	
	/**
	 * 查询充值失败统计结果
	 * @param firsttime
	 * @param lasttime
	 * @return
	 * @throws Exception
	 */
	public List findFailedPayTJList_all(String firsttime, String lasttime)throws Exception;
	List<PayTJ> findPayTJList_allGroupByChannels(List<Integer> channels,
			String beginTime, String endTime) throws Exception;
}
