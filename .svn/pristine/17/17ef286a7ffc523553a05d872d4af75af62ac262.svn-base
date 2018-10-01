package com.zbwang.calendar.service;

import java.util.List;
import java.util.Set;

import com.zbwang.calendar.domain.Book;
import com.zbwang.calendar.domain.BookNumber;
import com.zbwang.calendar.domain.BookSearchParam;

public interface IBookService {

	void addReadBook(Integer userId, Integer bookId);

	void addReadPerson(Integer bookId, Integer userId);

	Set<Integer> getReadBook(Integer userId);

	void prepareBookReadInfo(Book book, Integer userId);

	List<Book> searchBooks(BookSearchParam param, Integer userId);

	BookNumber getBookNumberInfo(Integer userId);

	void addBookNumber(Book book);

	void dropReadBook(Integer userId, Integer bookId);

	void dropReadPerson(Integer bookId, Integer userId);

	void changeBuyFlag(Short buyFlag);

	void dropBookNumber(Integer userId, Book book);
}
