package com.ruicai.basis.entity;

import java.util.Date;

/**
 * 充值方式统计表
 * @author liukw
 * date 2010-4-20
 */
public class PayTJ implements java.io.Serializable{	

	private static final long serialVersionUID = 1L;
	private int id;
	private String tjdate;
	private String ywid;
	private String channelid;
	
	private String paytype;	
	private int usernum;	
	private float paymoney;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTjdate() {
		return tjdate;
	}
	public void setTjdate(String tjdate) {
		this.tjdate = tjdate;
	}
	public String getYwid() {
		return ywid;
	}
	public void setYwid(String ywid) {
		this.ywid = ywid;
	}
	public String getChannelid() {
		return channelid;
	}
	public void setChannelid(String channelid) {
		this.channelid = channelid;
	}
	public String getPaytype() {
		return paytype;
	}
	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
	public int getUsernum() {
		return usernum;
	}
	public void setUsernum(int usernum) {
		this.usernum = usernum;
	}
	public float getPaymoney() {
		return paymoney;
	}
	public void setPaymoney(float paymoney) {
		this.paymoney = paymoney;
	}	
		
}
