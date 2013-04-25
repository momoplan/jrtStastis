package com.ruicai.basis.sys.business;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ruicai.basis.common.RollPage;
import com.ruicai.basis.entity.Menu;
import com.ruicai.basis.entity.Role;
import com.ruicai.basis.entity.RoleMenu;

public interface RoleManage {

	/**
	 * 返回查询总数
	 * 
	 * @param Role
	 * @return
	 * @throws Exception
	 */
	public Integer findListCount(Role instance) throws Exception ;
	
	/**
	 * 返回分页查询结果
	 * @param request
	 * @param Role
	 * @param rollPage
	 * @return
	 */
	public List<Role> findList(HttpServletRequest request, Role instance, RollPage rollPage) throws Exception ;
	
	/**
     * 返回实体
     * @param id
     * @return
     */
    public Role findById(java.lang.Integer id) ;
    
	/**
	 * 保存实体
	 * @param transientInstance
	 */
	public void save(Role instance,String[] autos);
	
	/**
	 * 更新实体
	 * @param transientInstance
	 */
	public void update(Role instance,String[] autos) throws Exception;
		
	/**
	 * 批量删除
	 * @param ids
	 * @throws Exception
	 */
	public void deleteJL(String ids) throws Exception ;
	
	/**
	 * 返回列表
	 * @return
	 */
	public List<Role> findAll() ;
	
	public List<RoleMenu> findRoleMenu(Integer roleid);
	
	public List<Menu> findUserMenu(Integer roleid);
	
	/**
	 * 用户所属菜单查询
	 * 
	 * @param roleid
	 * @param fid
	 * @return
	 */
	public List<Menu> findUserMenu(Integer roleid, Integer fid);
}
