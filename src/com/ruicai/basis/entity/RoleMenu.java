package com.ruicai.basis.entity;

import java.util.Date;

/**
 * 菜单表
 * @author liukw
 * date 2010-4-21
 */
public class RoleMenu implements java.io.Serializable{	

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String url;
	private Integer fid;
	private Integer ascid;
	
	private Integer status;	
	private String bz;
	private String bool;
	
	public String getBool() {
		return bool;
	}
	public void setBool(String bool) {
		this.bool = bool;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getFid() {
		return fid;
	}
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	public Integer getAscid() {
		return ascid;
	}
	public void setAscid(Integer ascid) {
		this.ascid = ascid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}	
	
}
