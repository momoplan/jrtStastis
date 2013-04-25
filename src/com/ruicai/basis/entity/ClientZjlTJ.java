package com.ruicai.basis.entity;

/**
 * 客户端装机量统计表
 * @author wnx
 * date 2011-8-11
 */
public class ClientZjlTJ implements java.io.Serializable{	

	private static final long serialVersionUID = 1L;
	private int id;
	private String statdate;
	private String coopid;
	private int zjl;
	public String getCoopid() {
		return coopid;
	}
	public void setCoopid(String coopid) {
		this.coopid = coopid;
	}
	public String getStatdate() {
		return statdate;
	}
	public void setStatdate(String statdate) {
		this.statdate = statdate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getZjl() {
		return zjl;
	}
	public void setZjl(int zjl) {
		this.zjl = zjl;
	}
	
	
	
	
	
		
}
