package com.zbwang.calendar.vo;

public class Page {
	private int total;
	private int pageSize;
	private int currentPage;
	private String url;

	public Page() {
	}

	public int getTotal() {
		return total;
	}

	public Page(int total, int pageSize, int currentPage, String url) {
		super();
		this.total = total;
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.url = url;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getTotalPage() {
		if (total == 0) {
			return 0;
		}
		return (total - 1) / pageSize + 1;
	}

	public int getStartPage() {
		return Math.max(1, currentPage - 3);
	}

	public int getEndPage() {
		if (currentPage < 4) {
			return 8;
		}
		return Math.min(getTotalPage(), currentPage + 3);
	}
}
