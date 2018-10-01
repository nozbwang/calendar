package com.zbwang.calendar.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zbwang.calendar.dao.IBookDao;
import com.zbwang.calendar.domain.Book;
import com.zbwang.calendar.domain.BookNumber;
import com.zbwang.calendar.domain.BookSearchParam;
import com.zbwang.calendar.service.IBookService;
import com.zbwang.calendar.service.IJedisService;

@Service
public class BookServiceImpl implements IBookService {

	// 一个人读过的书
	private static final String BK_READ_BOOK = "BK_READ_BOOK";
	// 一本书读过的人
	private static final String BK_READ_PERSON = "BK_READ_PERSON";
	// 所有图书
	private static final String BK_TOTAL_NUMBER = "BK_TOTAL_NUMBER";
	// 已买总数
	private static final String BK_BUY_NUMBER = "BK_BUY_NUMBER";
	// 想买总数
	private static final String BK_WANT_NUMBER = "BK_WANT_NUMBER";
	@Autowired
	private IJedisService jedisService;
	@Autowired
	private IBookDao bookDao;

	@Override
	public void addReadBook(Integer userId, Integer bookId) {
		jedisService.sadd(getReadBookKey(userId), String.valueOf(bookId));
	}

	@Override
	public void addReadPerson(Integer bookId, Integer userId) {
		jedisService.sadd(getReadPersonKey(userId), String.valueOf(bookId));
	}

	@Override
	public void dropReadBook(Integer userId, Integer bookId) {
		jedisService.srem(getReadBookKey(userId), String.valueOf(bookId));
	}

	@Override
	public void dropReadPerson(Integer bookId, Integer userId) {
		jedisService.srem(getReadPersonKey(userId), String.valueOf(bookId));
	}

	private String getReadBookKey(Integer userId) {
		return BK_READ_BOOK + ":" + userId;
	}

	private String getReadPersonKey(Integer bookId) {
		return BK_READ_PERSON + ":" + bookId;
	}

	@Override
	public Set<Integer> getReadBook(Integer userId) {
		Set<String> readBooks = jedisService.smembers(getReadPersonKey(userId));
		if (CollectionUtils.isNotEmpty(readBooks)) {
			return readBooks.stream().map(b -> NumberUtils.toInt(b)).collect(Collectors.toSet());
		}
		return Collections.emptySet();
	}

	@Override
	public List<Book> searchBooks(BookSearchParam param, Integer userId) {
		if (param.needSetReadBookIds()) {
			param.setReadBookIds(getReadBook(userId));
		}
		if (param.searchReadBook() && param.haveReadNoBooks()) {
			return Collections.emptyList();
		}
		return bookDao.searchBooks(param);
	}

	@Override
	public void prepareBookReadInfo(Book book, Integer userId) {
		Set<String> readBooks = jedisService.smembers(getReadPersonKey(userId));
		if (CollectionUtils.isNotEmpty(readBooks) && readBooks.contains(String.valueOf(book.getBookId()))) {
			book.setReadFlag(Book.READ);
		}
	}

	@Override
	public BookNumber getBookNumberInfo(Integer userId) {
		BookNumber bookNumber = new BookNumber();
		bookNumber.setTotalNumber(jedisService.getLong(BK_TOTAL_NUMBER));
		bookNumber.setWantNumber(jedisService.getLong(BK_WANT_NUMBER));
		bookNumber.setBuyNumber(jedisService.getLong(BK_BUY_NUMBER));
		bookNumber.setReadNumber(jedisService.scard(getReadBookKey(userId)));
		return bookNumber;
	}

	@Override
	public void changeBuyFlag(Short buyFlag) {
		if (Book.BUY.equals(buyFlag)) {
			jedisService.incr(BK_BUY_NUMBER);
			jedisService.decr(BK_WANT_NUMBER);
		}
		else {
			jedisService.incr(BK_WANT_NUMBER);
			jedisService.decr(BK_BUY_NUMBER);
		}
	}

	@Override
	public void addBookNumber(Book book) {
		if (book.wantBuy()) {
			jedisService.incr(BK_WANT_NUMBER);
		}
		else {
			jedisService.incr(BK_BUY_NUMBER);
		}
		jedisService.incr(BK_TOTAL_NUMBER);
	}

	@Override
	public void dropBookNumber(Integer userId, Book book) {
		if (book.wantBuy()) {
			jedisService.decr(BK_WANT_NUMBER);
		}
		else {
			jedisService.decr(BK_BUY_NUMBER);
		}
		jedisService.srem(getReadBookKey(userId), String.valueOf(book.getBookId()));
		jedisService.del(getReadPersonKey(book.getBookId()));
		jedisService.decr(BK_TOTAL_NUMBER);
	}
}
