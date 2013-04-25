package com.ruicai.basis.pv.business;

import java.util.List;

import com.ruicai.basis.entity.PVCount;
import com.ruicai.basis.entity.UserLog;

public interface UserLogManage {

	/**
     * 保存实体
     * @param transientInstance
     */
    public void save(UserLog userlog) ; 
    
    /**
	 * 返回最近200条记录
	 * @return
	 * @throws Exception
	 */
	public List<UserLog> findLast200List() throws Exception ;
	
	/**
	 * 返回用户日志信息
	 * @param tel
	 * @return
	 * @throws Exception
	 */
	public List<UserLog> findLogInfo(String tel) throws Exception ;
}
