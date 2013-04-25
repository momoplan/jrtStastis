package com.ruicai.basis.common;

public class RollPage {

	int rowCount = 0; // 记录总数

	int pageNum = 0;// 页总数

	int pageCur = 0;// 当前页数

	int pagePer = 10;// 页显示记录数

	int pageFirst = 0;

	public RollPage() {

	}

	/**
	 * 转入面数
	 */
	public RollPage(int pageCur) {
		this.pageCur = pageCur;
	}

	/**
	 * 输入总页数后进行初始化
	 */
	public void init() {
		pageCount();
		firstResult();
	}

	/**
	 * 计算出总页数
	 */
	private void pageCount() {
		if (this.rowCount % this.pagePer == 0) {
			this.pageNum = rowCount / this.pagePer;
		} else {
			this.pageNum = (rowCount / this.pagePer) + 1;
		}
	}

	/**
	 * 记录起始位
	 */
	private void firstResult() {
		this.pageFirst = (this.pageCur) * this.pagePer;
		if (this.pageFirst < 0) {
			this.pageFirst = 0;
		}
	}

	/**
	 * 返回当前页数
	 */
	public int getPageCur() {
		return pageCur;
	}

	/**
	 * 返回页总数
	 */
	public int getPageNum() {
		return pageNum;
	}

	/**
	 * 设置一页显示记录数
	 */
	public void setPagePer(int pagePer) {
		this.pagePer = pagePer;
	}

	/**
	 * 返回一页显示记录数
	 */
	public int getPagePer() {
		return pagePer;
	}

	/**
	 * 设置记录总数
	 */
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	/**
	 * 返回记录总数
	 */
	public int getRowCount() {
		return rowCount;
	}

	/**
	 * 记录开始位置
	 */
	public int getPageFirst() {
		return pageFirst;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public void setPageCur(int pageCur) {
		this.pageCur = pageCur;
	}

	public void setPageFirst(int pageFirst) {
		this.pageFirst = pageFirst;
	}

}
