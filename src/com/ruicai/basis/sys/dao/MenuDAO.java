package com.ruicai.basis.sys.dao;

import java.util.List;

import com.ruicai.basis.common.RollPage;
import com.ruicai.basis.entity.Menu;

public interface MenuDAO {
	
	/**
	 * 返回菜单查询信息
	 * @param menu
	 * @param rollPage
	 * @param resCount
	 * @return
	 * @throws Exception
	 */
	public List<Menu> findMenuList(Menu menu, RollPage rollPage, Integer resCount) throws Exception ;
	
	/**
	 * 返回菜单查询总数
	 * @param menu
	 * @return
	 * @throws Exception
	 */
    public Integer findMenuListCount(Menu menu) throws Exception ;
    
    /**
     * 更新菜单实体
     * @param transientInstance
     */
    public void update(Menu menu);
    
    /**
     * 返回MaxID
     * @return
     * @throws Exception
     */
    public Integer getMaxID() throws Exception ;
    
    /**
     * 返回菜单实体
     * @param id
     * @return
     */
    public Menu findById(Integer id) ;
    
    /**
     * 保存菜单实体
     * @param transientInstance
     */
    public void save(Menu menu) ;
    
    /**
     * 批量删除
     * @param ids
     * @throws Exception
     */
    public void delete(String ids) throws Exception ;
    
    public List<Menu> findAll() ;
}
