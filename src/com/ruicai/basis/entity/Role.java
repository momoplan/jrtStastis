package com.ruicai.basis.entity;

/**
 * 角色表
 * @author liukw
 * date 2010-4-28
 */
public class Role implements java.io.Serializable{	

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private Integer status;
	private String bz;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
