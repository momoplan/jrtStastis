package com.ruicai.basis.entity;

import java.util.Date;

/**
 * 购彩方式统计表
 * @author liukw
 * date 2010-4-19
 */
public class UserLog implements java.io.Serializable{	

	private static final long serialVersionUID = 1L;
	private int log_id;
	private String session_id;
	private Date log_time;
	private String tel_num;
	
	private String province_name;	
	private String city_name;
	private String tel_ua;
	private String cn_num;
	
	public int getLog_id() {
		return log_id;
	}
	public void setLog_id(int log_id) {
		this.log_id = log_id;
	}
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public Date getLog_time() {
		return log_time;
	}
	public void setLog_time(Date log_time) {
		this.log_time = log_time;
	}
	public String getTel_num() {
		return tel_num;
	}
	public void setTel_num(String tel_num) {
		this.tel_num = tel_num;
	}
	public String getProvince_name() {
		return province_name;
	}
	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getTel_ua() {
		return tel_ua;
	}
	public void setTel_ua(String tel_ua) {
		this.tel_ua = tel_ua;
	}
	public String getCn_num() {
		return cn_num;
	}
	public void setCn_num(String cn_num) {
		this.cn_num = cn_num;
	}	
}
