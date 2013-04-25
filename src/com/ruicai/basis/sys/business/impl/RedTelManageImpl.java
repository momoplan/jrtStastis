package com.ruicai.basis.sys.business.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ruicai.basis.common.ConvertLang;
import com.ruicai.basis.common.RollPage;
import com.ruicai.basis.entity.RedTel;
import com.ruicai.basis.sys.business.RedTelManage;
import com.ruicai.basis.sys.dao.RedTelDAO;

public class RedTelManageImpl implements RedTelManage{

	private RedTelDAO redtelDAO;
	

	public void setRedtelDAO(RedTelDAO redtelDAO) {
		this.redtelDAO = redtelDAO;
	}
	
	
	/**
	 * 获得实体
	 * @param id
	 * @return
	 */
	@Override
	public RedTel findById(java.lang.Integer id){
		return redtelDAO.findById(id);
	}
	
	/**
	 * 返回查询总数
	 * 
	 * @param RedTel
	 * @return
	 * @throws Exception
	 */
	@Override
	public Integer findListCount(RedTel instance) throws Exception {
        Integer resCount = 0;
        if(instance!=null){
        	resCount = redtelDAO.findListCount(instance);
		}		
		return resCount;
	}
	
	/**
	 * 返回分页查询结果
	 * @param request
	 * @param Role
	 * @param rollPage
	 * @return
	 */
	@Override
	public List<RedTel> findList(HttpServletRequest request, RedTel instance, RollPage rollPage) throws Exception {
        Integer resCount = 0;
		
		List<RedTel> list = new ArrayList<RedTel>();
		
		if(instance!=null){
			
			String page = request.getParameter("page");
			rollPage = new RollPage(page == null || page.equals("") ? 0: ConvertLang.convertint(page));
			rollPage.setPagePer(10);
			resCount = findListCount(instance);
			
			int rescount = resCount.intValue()/rollPage.getPagePer();
			
			if(rollPage.getPageCur()>rescount){
				rollPage.setPageCur(0);
			}
			
			list = redtelDAO.findList(instance, rollPage, resCount);

			request.setAttribute("rollPage", rollPage);
		}

		return list;
	}
    
	/**
	 * 保存实体
	 * @param transientInstance
	 */
	@Override
	public void save(RedTel instance){
		redtelDAO.save(instance);
	}
	
	/**
	 * 更新实体
	 * @param transientInstance
	 */
	@Override
	public void update(RedTel instance){
		redtelDAO.update(instance);
	}
		
	/**
	 * 批量删除
	 * @param ids
	 * @throws Exception
	 */
	@Override
	public void delete(String ids) throws Exception {
		redtelDAO.delete(ids);
	}	
	
	/**
	 * 判断手机号是否重复
	 * @param username
	 * @return
	 */
	@Override
	public boolean isSingleTel(String tel){
		List<RedTel> list = redtelDAO.findByProperty("tel", tel.trim());
		if(list.size()==0){
			return true;
		}else{
			return false;
		}
	}
}
