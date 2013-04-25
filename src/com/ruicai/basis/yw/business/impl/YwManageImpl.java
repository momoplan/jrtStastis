package com.ruicai.basis.yw.business.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ruicai.basis.common.ConvertLang;
import com.ruicai.basis.common.RollPage;
import com.ruicai.basis.entity.Yw;
import com.ruicai.basis.yw.business.YwManage;
import com.ruicai.basis.yw.dao.YwDAO;

public class YwManageImpl implements YwManage{

	private YwDAO ywDAO;

	public void setYwDAO(YwDAO ywDAO) {
		this.ywDAO = ywDAO;
	}

	/**
	 * 返回查询总数
	 * 
	 * @param yw
	 * @return
	 * @throws Exception
	 */
	public Integer findListCount(Yw instance) throws Exception {
        Integer resCount = 0;
		
		if(instance!=null){
			//���������������������������������
			resCount = ywDAO.findListCount(instance);
		}		
		return resCount;
	}
	
	/**
	 * 返回分页查询结果
	 * @param request
	 * @param menu
	 * @param rollPage
	 * @return
	 */
	public List<Yw> findList(HttpServletRequest request, Yw instance, RollPage rollPage) throws Exception{
        Integer resCount = 0;
		
		List<Yw> list = new ArrayList<Yw>();
		
		if(instance!=null){
			
			String page = request.getParameter("page");
			rollPage = new RollPage(page == null || page.equals("") ? 0: ConvertLang.convertint(page));
			rollPage.setPagePer(10);
			resCount = findListCount(instance);
			
			int rescount = resCount.intValue()/rollPage.getPagePer();
			
			if(rollPage.getPageCur()>rescount){
				rollPage.setPageCur(0);
			}
			
			list = ywDAO.findList(instance, rollPage, resCount);

			request.setAttribute("rollPage", rollPage);
		}

		return list;
	}	
	
	/**
     * 返回实体
     * @param id
     * @return
     */
    public Yw findById(java.lang.Integer id) {
    	return ywDAO.findById(id);
    }
    
	/**
	 * 保存实体
	 * @param transientInstance
	 */
	public void save(Yw instance){
		ywDAO.save(instance);
	}
	
	/**
	 * 更新实体
	 * @param transientInstance
	 */
	public void update(Yw instance){
		ywDAO.update(instance);
	}
		
	/**
	 * 批量删除
	 * @param ids
	 * @throws Exception
	 */
	public void delete(String ids) throws Exception {
		ywDAO.delete(ids);
	}
	
	/**
	 * 返回列表
	 * @return
	 */
	public List<Yw> findAll() {
		return ywDAO.findAll();	
	}
	
	/**
	 * 级联删除
	 * @param ids
	 * @throws Exception
	 */
	public void deleteJL(String ids) throws Exception {
		ywDAO.deleteJL(ids);
	}
	
	/**
	 * 返回实体
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public Yw findByCode(String code) {
		List list = ywDAO.findByProperty("code", code);
		Yw model = new Yw();
		model.setName(code);
		if(list.size()>0)
			model = (Yw)list.get(0);
		return model;
	}
	
}
