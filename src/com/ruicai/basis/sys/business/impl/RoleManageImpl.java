package com.ruicai.basis.sys.business.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ruicai.basis.common.ConvertLang;
import com.ruicai.basis.common.RollPage;
import com.ruicai.basis.entity.Menu;
import com.ruicai.basis.entity.Role;
import com.ruicai.basis.entity.RoleCfg;
import com.ruicai.basis.entity.RoleMenu;
import com.ruicai.basis.sys.business.RoleManage;
import com.ruicai.basis.sys.dao.RoleCfgDAO;
import com.ruicai.basis.sys.dao.RoleDAO;

public class RoleManageImpl implements RoleManage{

	private RoleDAO roleDAO;	
	private RoleCfgDAO roleCfgDAO;

	public void setRoleCfgDAO(RoleCfgDAO roleCfgDAO) {
		this.roleCfgDAO = roleCfgDAO;
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	/**
	 * 返回查询总数
	 * 
	 * @param Role
	 * @return
	 * @throws Exception
	 */
	public Integer findListCount(Role instance) throws Exception {
        Integer resCount = 0;
		
		if(instance!=null){
			//���������������������������������
			resCount = roleDAO.findListCount(instance);
		}		
		return resCount;
	}
	
	/**
	 * 返回列表
	 * @return
	 */
	public List<Role> findAll() {
		return roleDAO.findAll();
	}
	
	/**
	 * 返回分页查询结果
	 * @param request
	 * @param Role
	 * @param rollPage
	 * @return
	 */
	public List<Role> findList(HttpServletRequest request, Role instance, RollPage rollPage) throws Exception {
        Integer resCount = 0;
		
		List<Role> list = new ArrayList<Role>();
		
		if(instance!=null){
			
			String page = request.getParameter("page");
			rollPage = new RollPage(page == null || page.equals("") ? 0: ConvertLang.convertint(page));
			rollPage.setPagePer(10);
			resCount = findListCount(instance);
			
			int rescount = resCount.intValue()/rollPage.getPagePer();
			
			if(rollPage.getPageCur()>rescount){
				rollPage.setPageCur(0);
			}
			
			list = roleDAO.findList(instance, rollPage, resCount);

			request.setAttribute("rollPage", rollPage);
		}

		return list;
	}
	
	/**
     * 返回实体
     * @param id
     * @return
     */
    public Role findById(java.lang.Integer id) {
    	return roleDAO.findById(id);
    }
    
	/**
	 * 保存实体
	 * @param transientInstance
	 */
	public void save(Role instance,String[] autos){
		roleDAO.save(instance);
		
		if (autos != null) {
			for (String s : autos) {
				RoleCfg roleCfg = new RoleCfg();
				roleCfg.setMenuid(Integer.valueOf(s));
				roleCfg.setRoleid(instance.getId());
				roleCfgDAO.save(roleCfg);
			}
		}
	}
	
	public List<RoleMenu> findRoleMenu(Integer roleid){
		return roleCfgDAO.findRoleMenu(roleid);
	}
	
	/**
	 * 更新实体
	 * @param transientInstance
	 */
	public void update(Role instance,String[] autos) throws Exception{
		roleCfgDAO.deleteJL(String.valueOf(instance.getId()));
		for(String s : autos){
			RoleCfg roleCfg = new RoleCfg();
			roleCfg.setMenuid(Integer.valueOf(s));
			roleCfg.setRoleid(instance.getId());
			roleCfgDAO.save(roleCfg);
		}
		roleDAO.update(instance);
	}
		
	/**
	 * 批量删除
	 * @param ids
	 * @throws Exception
	 */
	public void deleteJL(String ids) throws Exception {
		roleDAO.deleteJL(ids);
		roleCfgDAO.deleteJL(ids);
	}
	
	public List<Menu> findUserMenu(Integer roleid){
		return roleCfgDAO.findUserMenu(roleid);
	}
	
	/**
	 * 用户所属菜单查询
	 * 
	 * @param roleid
	 * @param fid
	 * @return
	 */
	public List<Menu> findUserMenu(Integer roleid, Integer fid){
		return roleCfgDAO.findUserMenu(roleid, fid);
	}
		
}
