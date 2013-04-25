package com.ruicai.basis.entity;

import java.util.Date;

/**
 * 业务表
 * @author liukw
 * date 2010-4-22
 */
public class Yw implements java.io.Serializable{	

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String code;
	private String name;
	//private Integer bduserid;
	private User user;
	private Date cjdate;
	
	private Integer status;
	private String bz;
	/**
	 * 增加日期的查询条件
	 */
	String startTime ;//开始日期
	String endTime;//截止日期
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getCjdate() {
		return cjdate;
	}
	public void setCjdate(Date cjdate) {
		this.cjdate = cjdate;
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
