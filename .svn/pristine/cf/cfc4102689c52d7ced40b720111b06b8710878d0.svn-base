package com.zbwang.calendar.domain;

import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

public class BookSearchParam {

	private static final String FLAG_READ = "1";
	private static final String FLAG_UNREAD = "0";
	private String keyword;
	private Short buyFlagS;
	private String readFlagS;
	private String bookTypeS;
	private Set<Integer> readBookIds;

	public String getKeyword() {
		return StringUtils.trimToNull(keyword);
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Short getBuyFlagS() {
		return buyFlagS;
	}

	public void setBuyFlagS(Short buyFlag) {
		this.buyFlagS = buyFlag;
	}

	public boolean searchReadBook() {
		return FLAG_READ.equals(readFlagS);
	}

	public boolean searchUnReadBook() {
		return FLAG_UNREAD.equals(readFlagS);
	}

	public String getReadFlagS() {
		return readFlagS;
	}

	public void setReadFlagS(String readFlag) {
		this.readFlagS = readFlag;
	}

	public Set<Integer> getReadBookIds() {
		return readBookIds;
	}

	public void setReadBookIds(Set<Integer> readBookIds) {
		this.readBookIds = readBookIds;
	}

	public boolean haveReadNoBooks() {
		return CollectionUtils.isEmpty(readBookIds);
	}

	public boolean haveReadBooks() {
		return CollectionUtils.isNotEmpty(readBookIds);
	}

	public boolean needSetReadBookIds() {
		return readFlagS != null;
	}

	public String getBookTypeS() {
		return StringUtils.trimToNull(bookTypeS);
	}

	public void setBookTypeS(String bookType) {
		this.bookTypeS = bookType;
	}

	public String getSearchCondition() {
		StringBuilder searchCondition = new StringBuilder();
		searchCondition.append("readFlagS=").append(StringUtils.trimToEmpty(readFlagS));
		if (buyFlagS != null) {
			searchCondition.append("&buyFlagS=").append(buyFlagS);
		}
		searchCondition.append("&bookTypeS=").append(StringUtils.trimToEmpty(bookTypeS));
		return searchCondition.toString();
	}
}
