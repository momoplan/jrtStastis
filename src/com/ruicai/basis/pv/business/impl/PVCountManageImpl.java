package com.ruicai.basis.pv.business.impl;

import java.util.List;

import com.ruicai.basis.entity.PVCount;
import com.ruicai.basis.pv.business.PVCountManage;
import com.ruicai.basis.pv.dao.PVCountDAO;

public class PVCountManageImpl implements PVCountManage{

	private PVCountDAO pvCountDAO;

	public void setPvCountDAO(PVCountDAO pvCountDAO) {
		this.pvCountDAO = pvCountDAO;
	}

	/**
     * 保存实体
     * @param transientInstance
     */
    public void save(PVCount pvcount) {
    	pvCountDAO.save(pvcount);
    }
    
    /**
	 * 返回最近200条记录
	 * @return
	 * @throws Exception
	 */
	public List<PVCount> findList() throws Exception {
		return pvCountDAO.findList();
	}
		
}
