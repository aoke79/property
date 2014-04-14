package com.sinoframe.bean;

public class SysPageInfo {
	private int startIndex = 0;
	private int pageSize = 20;
	private long maxCount;
	private int currentPage;
	
	public SysPageInfo() {
		super();
	}
	public SysPageInfo(int startIndex, int pageSize) {
		super();
		this.startIndex = startIndex;
		this.pageSize = pageSize;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getMaxCount() {
		return maxCount;
	}
	public void setMaxCount(long maxCount) {
		this.maxCount = maxCount;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	

}
