package com.ruicai.basis.entity;

import java.util.Date;

/**
 * 用户行为统计表
 * @author liukw
 * date 2010-4-20
 */
public class UserActionTJ implements java.io.Serializable{	

	private static final long serialVersionUID = 1L;
	
	private int id;
	private Date tjdate;
	private int indexvn;
	private int popvn;	
	private int payvn;

	private float paymoney;	
	private float realPaymoney;	
	private int psoftn;
	private int loginn;	
	private int getmoneyn;
	private int buyn;
	
	private int payn;

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

	public int getIndexvn() {
		return indexvn;
	}

	public void setIndexvn(int indexvn) {
		this.indexvn = indexvn;
	}

	public int getPopvn() {
		return popvn;
	}

	public void setPopvn(int popvn) {
		this.popvn = popvn;
	}

	public int getPayvn() {
		return payvn;
	}

	public void setPayvn(int payvn) {
		this.payvn = payvn;
	}

	public float getPaymoney() {
		return paymoney;
	}

	public void setPaymoney(float paymoney) {
		this.paymoney = paymoney;
	}

	public int getPsoftn() {
		return psoftn;
	}

	public void setPsoftn(int psoftn) {
		this.psoftn = psoftn;
	}

	public int getLoginn() {
		return loginn;
	}

	public void setLoginn(int loginn) {
		this.loginn = loginn;
	}

	public int getGetmoneyn() {
		return getmoneyn;
	}

	public void setGetmoneyn(int getmoneyn) {
		this.getmoneyn = getmoneyn;
	}

	public int getBuyn() {
		return buyn;
	}

	public void setBuyn(int buyn) {
		this.buyn = buyn;
	}

	public int getPayn() {
		return payn;
	}

	public void setPayn(int payn) {
		this.payn = payn;
	}

	public float getRealPaymoney() {
		return realPaymoney;
	}

	public void setRealPaymoney(float realPaymoney) {
		this.realPaymoney = realPaymoney;
	}	
}
