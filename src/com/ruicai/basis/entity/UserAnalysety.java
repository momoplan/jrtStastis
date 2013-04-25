package com.ruicai.basis.entity;

import java.util.Date;

/**
 * 用户分析统计表
 * @author liukw
 * date 2010-4-19
 */
public class UserAnalysety implements java.io.Serializable{	

	private static final long serialVersionUID = 1L;
	private int id;
	private Date tjdate;
	private int regnum;
	private int silentnum;
	
	private int activenum;	
	private int vipnum;	
	private int escapenum;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getTjdate() {
		return tjdate;
	}
	public void setTjdate(Date tjdate) {
		this.tjdate = tjdate;
	}
	public int getRegnum() {
		return regnum;
	}
	public void setRegnum(int regnum) {
		this.regnum = regnum;
	}
	public int getSilentnum() {
		return silentnum;
	}
	public void setSilentnum(int silentnum) {
		this.silentnum = silentnum;
	}
	public int getActivenum() {
		return activenum;
	}
	public void setActivenum(int activenum) {
		this.activenum = activenum;
	}
	public int getVipnum() {
		return vipnum;
	}
	public void setVipnum(int vipnum) {
		this.vipnum = vipnum;
	}
	public int getEscapenum() {
		return escapenum;
	}
	public void setEscapenum(int escapenum) {
		this.escapenum = escapenum;
	}
		
}
