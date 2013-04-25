package com.ruicai.basis.entity;

/**
 * 客户端装机量统计表
 * @author wnx
 * date 2011-8-11
 */
public class Tbl_userinfo implements java.io.Serializable{	

	private int id;
	private String imei;
	private String userno;
	private String channel;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getUserno() {
		return userno;
	}
	public void setUserno(String userno) {
		this.userno = userno;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	
	
		
}
