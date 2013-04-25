package com.ruicai.basis.channel.business.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ruicai.basis.channel.business.UserCfgManage;
import com.ruicai.basis.channel.dao.UserCfgDAO;
import com.ruicai.basis.common.ConvertLang;
import com.ruicai.basis.common.RollPage;
import com.ruicai.basis.entity.UserCfg;

public class UserCfgManageImpl implements UserCfgManage{

	private UserCfgDAO usercfgDAO;
	
	public void setUsercfgDAO(UserCfgDAO usercfgDAO) {
		this.usercfgDAO = usercfgDAO;
	}

	/**
	 * 返回查询总数
	 * 
	 * @param yw
	 * @return
	 * @throws Exception
	 */
	public Integer findListCount(UserCfg instance) throws Exception {
		return usercfgDAO.findListCount(instance);
	}
	
	
	/**
     * 返回实体
     * @param id
     * @return
     */
    public UserCfg findById(java.lang.Integer id) {
    	return usercfgDAO.findById(id);
    }
    
	/**
	 * 保存实体
	 * @param transientInstance
	 * @throws Exception 
	 */
	public void save(UserCfg instance) {
		   usercfgDAO.save(instance);
	}
	
	/**
	 * 更新实体
	 * @param transientInstance
	 */
	public void update(UserCfg instance){
		usercfgDAO.update(instance);
	}
		
	/**
	 * 批量删除
	 * @param ids
	 * @throws Exception
	 */
	public void delete(String ids) throws Exception {
		usercfgDAO.delete(ids);
	}
	
	/**
	 * 返回分页查询结果
	 * @param request
	 * @param Role
	 * @param rollPage
	 * @return
	 */
	@Override
	public List<UserCfg> findList(HttpServletRequest request, UserCfg instance, RollPage rollPage) throws Exception {
        Integer resCount = 0;
		
		List<UserCfg> list = new ArrayList<UserCfg>();
		
		if(instance!=null){
			
			String page = request.getParameter("page");
			rollPage = new RollPage(page == null || page.equals("") ? 0: ConvertLang.convertint(page));
			rollPage.setPagePer(10);
			resCount = findListCount(instance);
			
			int rescount = resCount.intValue()/rollPage.getPagePer();
			
			if(rollPage.getPageCur()>rescount){
				rollPage.setPageCur(0);
			}
			
			list = usercfgDAO.findList(instance, rollPage, resCount);

			request.setAttribute("rollPage", rollPage);
		}

		return list;
	}
}
