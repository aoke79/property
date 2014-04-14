package com.sinoframe.bean;

public class SysOrderByInfo {
	private String orderColumn = "";
	private String orderAsc = "ASC";
	private boolean ifDate;
	private String leftJoinColumn;
	
	public SysOrderByInfo() {
		super();
	}
	public SysOrderByInfo(String orderColumn, String orderAsc) {
		super();
		this.orderColumn = orderColumn;
		this.orderAsc = orderAsc;
	}
	public String getOrderColumn() {
		return orderColumn;
	}
	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}
	public String getOrderAsc() {
		return orderAsc;
	}
	public void setOrderAsc(String orderAsc) {
		this.orderAsc = orderAsc;
	}
	public boolean isIfDate() {
		return ifDate;
	}
	public void setIfDate(boolean ifDate) {
		this.ifDate = ifDate;
	}
	public String getLeftJoinColumn() {
		return leftJoinColumn;
	}
	public void setLeftJoinColumn(String leftJoinColumn) {
		this.leftJoinColumn = leftJoinColumn;
	}

}
