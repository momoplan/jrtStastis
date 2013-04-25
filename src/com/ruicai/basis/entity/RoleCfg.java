package com.ruicai.basis.entity;

/**
 * 菜单表
 * @author liukw
 * date 2010-4-21
 */
public class RoleCfg implements java.io.Serializable{	

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer roleid;
	private Integer menuid;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRoleid() {
		return roleid;
	}
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
	public Integer getMenuid() {
		return menuid;
	}
	public void setMenuid(Integer menuid) {
		this.menuid = menuid;
	}	
		
}
