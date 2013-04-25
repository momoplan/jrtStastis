package com.ruicai.basis.tj.dao;

import java.util.List;

import com.ruicai.basis.entity.PvTJ;
import com.ruicai.basis.entity.User;

public interface PvTJDAO {

	/**
	 * 返回PV统计结果
	 * @param pvTJ
	 * @param firsttime
	 * @param lasttime
	 * @return
	 */
	public List<PvTJ> findPvTJList_all(PvTJ pvTJ,String firsttime,String lasttime,String orderLine,String orderRule) throws Exception; 
	
	public List<PvTJ> findPvTJList_hezuo(User user, String firsttime, String lasttime) throws Exception ;
}
