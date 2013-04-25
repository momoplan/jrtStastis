package com.ruicai.basis.sys.business;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ruicai.basis.common.RollPage;
import com.ruicai.basis.entity.Menu;

public interface MenuManage {

	/**
	 * 返回菜单查询信息
	 * @param menu
	 * @param rollPage
	 * @param resCount
	 * @return
	 * @throws Exception
	 */
	public List<Menu> findMenuList(HttpServletRequest request, Menu menu, RollPage rollPage) throws Exception ;
	
	/**
	 * 返回菜单查询总数
	 * @param menu
	 * @return
	 * @throws Exception
	 */
    public Integer findMenuListCount(Menu menu) throws Exception ;    
    
    /**
     * 返回菜单实体
     * @param id
     * @return
     */
    public Menu findById(java.lang.Integer id) ;
    
    /**
     * 保存菜单实体
     * @param transientInstance
     */
    public void save(Menu menu);
    
    /**
     * 更新菜单实体
     * @param transientInstance
     */
    public void update(Menu menu);
    
    /**
     * 批量删除
     * @param ids
     * @throws Exception
     */
    public void delete(String ids) throws Exception ;
    
    public List findAll() ;
}
