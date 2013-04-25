package com.ruicai.basis.sys.business.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ruicai.basis.common.ConvertLang;
import com.ruicai.basis.common.RollPage;
import com.ruicai.basis.entity.Menu;
import com.ruicai.basis.sys.business.MenuManage;
import com.ruicai.basis.sys.dao.MenuDAO;

public class MenuManageImpl implements MenuManage{

	private MenuDAO menuDAO;

	public void setMenuDAO(MenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}	
	
	/**
	 * 返回菜单查询总数
	 * @param menu
	 * @return
	 * @throws Exception
	 */
    public Integer findMenuListCount(Menu menu) throws Exception {
        
    	Integer resCount = 0;
		
		if(menu!=null){
			resCount = menuDAO.findMenuListCount(menu);
		}		
		return resCount;
    }
	
    /**
	 * 返回菜单查询信息
	 * @param menu
	 * @param rollPage
	 * @param resCount
	 * @return
	 * @throws Exception
	 */
	public List<Menu> findMenuList(HttpServletRequest request, Menu menu, RollPage rollPage) 
	    throws Exception {

		Integer resCount = 0;
		
		List<Menu> list = new ArrayList<Menu>();
		
		if(menu!=null){
			
			String page = request.getParameter("page");
			rollPage = new RollPage(page == null || page.equals("") ? 0: ConvertLang.convertint(page));
			rollPage.setPagePer(10);
			resCount = findMenuListCount(menu);
			
			int rescount = resCount.intValue()/rollPage.getPagePer();
			
			if(rollPage.getPageCur()>rescount){
				rollPage.setPageCur(0);
			}
			
			list = menuDAO.findMenuList(menu, rollPage, resCount);

			request.setAttribute("rollPage", rollPage);
		}

		return list;
	}
	
	/**
     * 更新菜单实体
     * @param transientInstance
     */
    public void update(Menu menu){
    	menuDAO.update(menu);
    }
    
    /**
     * 返回菜单实体
     * @param id
     * @return
     */
    public Menu findById(java.lang.Integer id) {
    	return menuDAO.findById(id);
    }
    
    /**
     * 保存菜单实体
     * @param transientInstance
     */
    public void save(Menu menu) {
    	menuDAO.save(menu);
    }
    
    /**
     * 批量删除
     * @param ids
     * @throws Exception
     */
    public void delete(String ids) throws Exception {
    	menuDAO.delete(ids);
    }
    
    public List findAll() {
    	return menuDAO.findAll();
    }
		
}
