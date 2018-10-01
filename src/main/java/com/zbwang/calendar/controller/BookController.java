package com.zbwang.calendar.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;
import com.zbwang.calendar.constant.Constants;
import com.zbwang.calendar.dao.IBookDao;
import com.zbwang.calendar.domain.Book;
import com.zbwang.calendar.domain.BookSearchParam;
import com.zbwang.calendar.service.IAttachService;
import com.zbwang.calendar.service.IBookService;

import static com.zbwang.calendar.constant.Constants.B_TYPE_BOOK;
import static com.zbwang.calendar.constant.Constants.B_TYPE_VOICE;

@Controller
@RequestMapping("/book")
public class BookController extends AbstractController {

	@Autowired
	private IBookDao bookDao;
	@Autowired
	private IAttachService attachService;
	@Autowired
	private IBookService bookService;

	@RequestMapping("")
	public ModelAndView book() {
		List<Book> books = bookDao.listAllBooks();
		attachService.prepareBookAttach(books);
		Map<String, Object> modelMap = new HashMap<String, Object>(2);
		modelMap.put("books", books);
		modelMap.put("bookNumber", bookService.getBookNumberInfo(getUser().getUserId()));
		return getBaseModelAndView(modelMap, "pages/book");
	}

	@RequestMapping("/search")
	public ModelAndView search(BookSearchParam param) {
		List<Book> books = bookService.searchBooks(param, getUser().getUserId());
		attachService.prepareBookAttach(books);
		Map<String, Object> modelMap = new HashMap<String, Object>(2);
		modelMap.put("books", books);
		modelMap.put("param", param);
		modelMap.put("bookNumber", bookService.getBookNumberInfo(getUser().getUserId()));
		return getBaseModelAndView(modelMap, "pages/book");
	}

	@RequestMapping("add")
	public ModelAndView add(BookSearchParam param) {
		Map<String, Object> modelMap = new HashMap<String, Object>(2);
		modelMap.put("param", param);
		modelMap.put("bookNumber", bookService.getBookNumberInfo(getUser().getUserId()));
		return getBaseModelAndView(modelMap, "pages/book_add");
	}

	@RequestMapping("read")
	public void read(HttpServletRequest request, HttpServletResponse response, String action) throws IOException {
		Integer userId = getUser().getUserId();
		Integer bookId = NumberUtils.toInt(request.getParameter("bookId"));
		if ("mark-read".equals(action)) {
			bookService.addReadBook(userId, bookId);
			bookService.addReadPerson(bookId, userId);
		}
		else {
			bookService.dropReadBook(userId, bookId);
			bookService.dropReadPerson(bookId, userId);
		}
		responseAjax(response, true);
	}

	@RequestMapping("buy")
	public void buy(HttpServletResponse response, Integer bookId, String action) throws IOException {
		if ("mark-buy".equals(action)) {
			bookDao.updateBookBuyFlag(Book.BUY, bookId);
			bookService.changeBuyFlag(Book.BUY);
		}
		else {
			bookDao.updateBookBuyFlag(Book.UN_BUY, bookId);
			bookService.changeBuyFlag(Book.UN_BUY);
		}
		responseAjax(response, true);
	}

	@RequestMapping("detail/{bookId}")
	public ModelAndView bookDetail(@PathVariable Integer bookId, BookSearchParam param) {
		Book book = bookDao.selectByPrimaryKey(bookId);
		attachService.prepareBookAttach(Lists.newArrayList(book));
		bookService.prepareBookReadInfo(book, getUser().getUserId());
		Map<String, Object> modelMap = new HashMap<String, Object>(2);
		modelMap.put("bookNumber", bookService.getBookNumberInfo(getUser().getUserId()));
		modelMap.put("book", book);
		modelMap.put("param", param);
		return getBaseModelAndView(modelMap, "pages/book_detail");
	}

	@RequestMapping("alter/{bookId}")
	public ModelAndView alterBook(@PathVariable Integer bookId, BookSearchParam param) {
		Book book = bookDao.selectByPrimaryKey(bookId);
		attachService.prepareBookAttach(Lists.newArrayList(book));
		bookService.prepareBookReadInfo(book, getUser().getUserId());
		Map<String, Object> modelMap = new HashMap<String, Object>(2);
		modelMap.put("bookNumber", bookService.getBookNumberInfo(getUser().getUserId()));
		modelMap.put("book", book);
		modelMap.put("param", param);
		return getBaseModelAndView(modelMap, "pages/book_alter");
	}

	@RequestMapping("addBook")
	public String addBook(Book book, BookSearchParam param, Integer attachId) {
		book.setUserId(getUser().getUserId());
		bookDao.insert(book);
		bookService.addBookNumber(book);
		if (book.isRead()) {
			bookService.addReadBook(getUser().getUserId(), book.getBookId());
			bookService.addReadPerson(book.getBookId(), getUser().getUserId());
		}
		if (Objects.nonNull(attachId)) {
			attachService.updateAttachBelong(attachId, B_TYPE_BOOK, book.getBookId(), getUser());
		}
		return redirectTo("/book/detail/" + book.getBookId() + "?" + param.getSearchCondition());
	}

	@RequestMapping("alterBook")
	public String alterBook(Book book, BookSearchParam param, Integer attachId) {
		bookDao.updateBook(book);
		if (Objects.nonNull(attachId)) {
			attachService.deleteLocalPicture(getUser().getUserId(), B_TYPE_BOOK, book.getBookId());
			attachService.updateAttachBelong(attachId, B_TYPE_BOOK, book.getBookId(), getUser());
		}
		return redirectTo("/book/detail/" + book.getBookId() + "?" + param.getSearchCondition());
	}

	@RequestMapping("deleteBook/{bookId}")
	public String deleteBook(@PathVariable Integer bookId, BookSearchParam param) {
		Book book = bookDao.selectByPrimaryKey(bookId);
		bookDao.deleteBook(bookId);
		attachService.deleteLocalPicture(getUser().getUserId(), B_TYPE_BOOK, book.getBookId());
		bookService.dropBookNumber(getUser().getUserId(), book);
		return redirectTo("/book/search" + "?" + param.getSearchCondition());
	}
}
