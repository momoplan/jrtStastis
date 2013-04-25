package com.ruicai.basis.entity;

import java.util.Date;

/**
 * 业务推广PV统计表
 * @author liukw
 * date 2010-4-19
 */
public class PvTJ {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String tjdate;
	private String ywid;
	private String channelid;
	private String province;
	
	private int visitnum;

	private int regnum;
	private int paynum;
	private float percent;
	private int uvnum;
	
	// 临时值
	private int channel;
	
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
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public int getVisitnum() {
		return visitnum;
	}
	public void setVisitnum(int visitnum) {
		this.visitnum = visitnum;
	}
	public int getRegnum() {
		return regnum;
	}
	public void setRegnum(int regnum) {
		this.regnum = regnum;
	}
	public int getPaynum() {
		return paynum;
	}
	public void setPaynum(int paynum) {
		this.paynum = paynum;
	}
	public float getPercent() {
		return percent;
	}
	public void setPercent(float percent) {
		this.percent = percent;
	}
	public int getChannel() {
		return channel;
	}
	public void setChannel(int channel) {
		this.channel = channel;
	}
	public int getUvnum() {
		return uvnum;
	}
	public void setUvnum(int uvnum) {
		this.uvnum = uvnum;
	}	
}
