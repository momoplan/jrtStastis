package com.ruicai.basis.pv.business;

import java.util.List;

import com.ruicai.basis.entity.PVCount;

public interface PVCountManage {

	/**
     * 保存实体
     * @param transientInstance
     */
    public void save(PVCount pvcount) ; 
    
    /**
	 * 返回最近200条记录
	 * @return
	 * @throws Exception
	 */
	public List<PVCount> findList() throws Exception ;
}
