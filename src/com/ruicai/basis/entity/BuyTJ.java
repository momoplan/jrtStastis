package com.ruicai.basis.entity;

import java.util.Date;

/**
 * 购彩方式统计表
 * @author liukw
 * date 2010-4-19
 */
public class BuyTJ implements java.io.Serializable{	

	private static final long serialVersionUID = 1L;
	private int id;
	private String tjdate;
	private String ywid;
	private String channelid;
	
	private String buytype;	
	private int usernum;	
	private float buymoney;
	
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
	public String getBuytype() {
		return buytype;
	}
	public void setBuytype(String buytype) {
		this.buytype = buytype;
	}
	public int getUsernum() {
		return usernum;
	}
	public void setUsernum(int usernum) {
		this.usernum = usernum;
	}
	public float getBuymoney() {
		return buymoney;
	}
	public void setBuymoney(float buymoney) {
		this.buymoney = buymoney;
	}	
}
