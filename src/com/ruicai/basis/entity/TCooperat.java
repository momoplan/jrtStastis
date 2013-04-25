package com.ruicai.basis.entity;

import java.io.Serializable;

public class TCooperat implements Serializable {
	private Long id;// 主键
	private String channlId;// 渠道编号（id）
	private String cooperatType;// 合作方式
	private String countType;// 计算方式(元/个,百分比，元/月)等
	private Double count = new Double(0.00);// 合作金额(元)
    private Double cpaCount = new Double(0.00); //cpa金额
    private Double cpcCount = new Double(0.00); //cpc金额
    private Double cpsCount = new Double(0.00); //cps金额
    private Double rivetCount = new Double(0.00); //固定金额总计
    
	public Double getCpaCount() {
		return cpaCount;
	}

	public void setCpaCount(Double cpaCount) {
		this.cpaCount = cpaCount;
	}

	public Double getCpcCount() {
		return cpcCount;
	}

	public void setCpcCount(Double cpcCount) {
		this.cpcCount = cpcCount;
	}

	public Double getCpsCount() {
		return cpsCount;
	}

	public void setCpsCount(Double cpsCount) {
		this.cpsCount = cpsCount;
	}

	public Double getRivetCount() {
		return rivetCount;
	}

	public void setRivetCount(Double rivetCount) {
		this.rivetCount = rivetCount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getChannlId() {
		return channlId;
	}

	public void setChannlId(String channlId) {
		this.channlId = channlId;
	}

	public String getCooperatType() {
		return cooperatType;
	}

	public void setCooperatType(String cooperatType) {
		this.cooperatType = cooperatType;
	}

	public String getCountType() {
		return countType;
	}

	public void setCountType(String countType) {
		this.countType = countType;
	}

	public Double getCount() {
		return count;
	}

	public void setCount(Double count) {
		this.count = count;
	}

}
