package com.ruicai.basis.entity;

import java.util.Date;

/**
 * 业务表
 * @author liukw
 * date 2010-4-23
 */
public class Channel implements java.io.Serializable{	

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Yw yw;	
	private String code;
	private String name;
	private String tel;
	
	//private Integer bduserid;	
	private User user;
	private Date cjdate;	
	private Integer status;
	private String bz;
	private String url;
	
	private Double paymoney = new Double(0.00);//充值总金额
	private Double buymoney = new Double(0.00);//购彩总金额
	private Double balance  = new Double(0.00);//结算金额
	private Long regnum  = new Long(0);//注册用户数
	private Long regPaynum  = new Long(0);//注册当天的充值用户数
	private Long paynum  = new Long(0);//当天充了值的所有用户数
	private Long visitnum  = new Long(0);;//访问用户数
	
	private Long days = new Long(0);//天数         开始与截止日期相差的天数
	private int months ;//日期相差的月数
	
	public int getMonths() {
		return months;
	}
	public void setMonths(int months) {
		this.months = months;
	}
	public Long getDays() {
		return days;
	}
	public void setDays(Long days) {
		this.days = days;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Double getPaymoney() {
		return paymoney;
	}
	public void setPaymoney(Double paymoney) {
		this.paymoney = paymoney;
	}
	public Double getBuymoney() {
		return buymoney;
	}
	public void setBuymoney(Double buymoney) {
		this.buymoney = buymoney;
	}
	public Long getRegnum() {
		return regnum;
	}
	public void setRegnum(Long regnum) {
		this.regnum = regnum;
	}
	public Long getVisitnum() {
		return visitnum;
	}
	public void setVisitnum(Long visitnum) {
		this.visitnum = visitnum;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public Yw getYw() {
		return yw;
	}
	public void setYw(Yw yw) {
		this.yw = yw;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Long getRegPaynum() {
		return regPaynum;
	}
	public void setRegPaynum(Long regPaynum) {
		this.regPaynum = regPaynum;
	}
	public Long getPaynum() {
		return paynum;
	}
	public void setPaynum(Long paynum) {
		this.paynum = paynum;
	}	
	
}
