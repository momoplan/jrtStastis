package com.ruicai.basis.sys.dao;

import java.util.List;

import com.ruicai.basis.entity.Menu;
import com.ruicai.basis.entity.RoleCfg;
import com.ruicai.basis.entity.RoleMenu;

public interface RoleCfgDAO {

	/**
	 * 保存角色配置信息
	 * 
	 * @param transientInstance
	 */
	public void save(RoleCfg transientInstance);

	/**
	 * 级联删除角色配置信息
	 * 
	 * @param roleids
	 * @throws Exception
	 */
	public void deleteJL(String roleids) throws Exception;

	/**
	 * 用户所属菜单查询
	 * 
	 * @param roleid
	 * @return
	 */
	public List<Menu> findUserMenu(Integer roleid);

	/**
	 * 用户所属菜单查询
	 * 
	 * @param roleid
	 * @param fid
	 * @return
	 */
	public List<Menu> findUserMenu(Integer roleid, Integer fid);

	/**
	 * 用户所属菜单查询(带归属标记)
	 * 
	 * @param roleid
	 * @return
	 */
	public List<RoleMenu> findRoleMenu(Integer roleid);
}
