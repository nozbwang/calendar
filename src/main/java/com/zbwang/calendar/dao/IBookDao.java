package com.zbwang.calendar.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zbwang.calendar.domain.Book;
import com.zbwang.calendar.domain.BookSearchParam;

public interface IBookDao {

	List<Book> listAllBooks();

	List<Book> searchBooks(@Param("param") BookSearchParam param);

	int deleteBook(Integer bookId);

	int insert(Book record);

	Book selectByPrimaryKey(Integer bookId);

	int updateBook(Book record);

	int updateBookBuyFlag(@Param("buyFlag") Short buyFlag, @Param("bookId") Integer bookId);
}