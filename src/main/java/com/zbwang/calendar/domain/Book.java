package com.zbwang.calendar.domain;

import java.util.Date;

import com.zbwang.calendar.constant.Constants;

public class Book {

	public static final Short UN_BUY = 0;
	public static final Short BUY = 1;
	public static final Short UN_READ = 0;
	public static final Short READ = 1;
	private Integer bookId;
	private Integer userId;
	private String bookTitle;
	private String author;
	private String bookDetail;
	private Date addTime;
	private Date updateTime;
	/**
	 * 1-小说 2-专业书 3-生活 0-其它
	 */
	private String bookType;
	private Attach cover;
	private Short buyFlag;
	private Short readFlag;

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getBookDetail() {
		return bookDetail;
	}

	public void setBookDetail(String bookDetail) {
		this.bookDetail = bookDetail;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getBookType() {
		return bookType;
	}

	public String getBookTypeDescription() {
		return Constants.catalogMap.get(bookType);
	}

	public void setBookType(String bookType) {
		this.bookType = bookType;
	}

	public Attach getCover() {
		return cover;
	}

	public void setCover(Attach cover) {
		this.cover = cover;
	}

	public Short getBuyFlag() {
		return buyFlag;
	}

	public void setBuyFlag(Short buyFlag) {
		this.buyFlag = buyFlag;
	}

	public boolean wantBuy() {
		return UN_BUY.equals(buyFlag);
	}

	public boolean isRead() {
		return READ.equals(readFlag);
	}

	public Short getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(Short readFlag) {
		this.readFlag = readFlag;
	}
}