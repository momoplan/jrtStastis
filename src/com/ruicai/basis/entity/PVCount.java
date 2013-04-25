package com.ruicai.basis.entity;

import java.util.Date;

/**
 * 用户PV日志表
 * @author liukw
 * date 2010-4-19
 */
public class PVCount implements java.io.Serializable{	

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Date tsj;
	private Integer type;
	private Integer num;	
	private String channelid;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getTsj() {
		return tsj;
	}
	public void setTsj(Date tsj) {
		this.tsj = tsj;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getChannelid() {
		return channelid;
	}
	public void setChannelid(String channelid) {
		this.channelid = channelid;
	}		
		
}
