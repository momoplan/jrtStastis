package com.ruicai.basis.entity;

public class UserInfo {

	private String userno;
	private String tel;
	private String certid;
	private String channel;
	private String regtime;
	
	private String modtime;	
	private float totalbetamt;
	private float totalbepositamt;
	private float balance;
	
	
	public String getUserno() {
		return userno;
	}

	public void setUserno(String userno) {
		this.userno = userno;
	}

	public float getTotalbetamt() {
		return totalbetamt;
	}

	public void setTotalbetamt(float totalbetamt) {
		this.totalbetamt = totalbetamt;
	}

	public float getTotalbepositamt() {
		return totalbepositamt;
	}

	public void setTotalbepositamt(float totalbepositamt) {
		this.totalbepositamt = totalbepositamt;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCertid() {
		return certid;
	}

	public void setCertid(String certid) {
		this.certid = certid;
	}

	public String getRegtime() {
		return regtime;
	}

	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}

	public String getModtime() {
		return modtime;
	}

	public void setModtime(String modtime) {
		this.modtime = modtime;
	}
	
}
