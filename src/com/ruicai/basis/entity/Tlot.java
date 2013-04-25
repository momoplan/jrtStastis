package com.ruicai.basis.entity;

public class Tlot {
  private String lotno;
  private String flowno;
  private long amt;
  public long getAmt() {
	return amt;
}
public void setAmt(long amt) {
	this.amt = amt;
}
public String getFlowno() {
	return flowno;
}
public void setFlowno(String flowno) {
	this.flowno = flowno;
}
private long count;
  private String batchcode;
  private String ordertime;
public String getOrdertime() {
	return ordertime;
}
public void setOrdertime(String ordertime) {
	this.ordertime = ordertime;
}
public String getLotno() {
	return lotno;
}
public void setLotno(String lotno) {
	this.lotno = lotno;
}
public long getCount() {
	return count;
}
public void setCount(long count) {
	this.count = count;
}
public String getBatchcode() {
	return batchcode;
}
public void setBatchcode(String batchcode) {
	this.batchcode = batchcode;
}
  
}
