package com.zbwang.calendar.domain;

public class BookNumber {

	private long totalNumber;
	private long readNumber;
	private long unreadNumber;
	private long wantNumber;
	private long buyNumber;

	public long getBuyNumber() {
		return buyNumber;
	}

	public void setBuyNumber(long buyNumber) {
		this.buyNumber = buyNumber;
	}

	public long getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(long totalNumber) {
		this.totalNumber = totalNumber;
	}

	public long getReadNumber() {
		return readNumber;
	}

	public void setReadNumber(long readNumber) {
		this.readNumber = readNumber;
	}

	public long getUnreadNumber() {
		return totalNumber - readNumber;
	}

	public void setUnreadNumber(long unreadNumber) {
		this.unreadNumber = unreadNumber;
	}

	public long getWantNumber() {
		return wantNumber;
	}

	public void setWantNumber(long wantNumber) {
		this.wantNumber = wantNumber;
	}

	@Override
	public String toString() {
		return "BookNumber [totalNumber=" + totalNumber + ", readNumber=" + readNumber + ", unreadNumber=" + unreadNumber + ", wantNumber="
				+ wantNumber + "]";
	}
}
