package com.ruicai.basis.entity;


/**
 * 红名单列表
 * @author liukw
 * date 2010-4-21
 */
public class RedTel implements java.io.Serializable{	

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String tel;
	private String bz;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}	
	
}
