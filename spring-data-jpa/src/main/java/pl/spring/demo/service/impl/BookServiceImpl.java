package pl.spring.demo.service.impl;

import pl.spring.demo.dao.BookDao;
import pl.spring.demo.mapper.BookMapper;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDao bookDao;
	@Autowired
	private BookMapper bookMapper;

	@Override
	public List<BookTo> findAllBooks() {
		return bookMapper.toListBookTo(bookDao.findAll());
	}

	@Override
	public List<BookTo> findBooksByTitle(String title) {
		return bookMapper.toListBookTo(bookDao.findBookByTitle(title));
	}

	@Override
	public List<BookTo> findBooksByAuthor(String author) {
		return bookMapper.toListBookTo(bookDao.findBooksByAuthor(author));
	}

	@Override
	public BookTo saveBook(BookTo book) {
		return bookMapper.toBookTo(bookDao.save(bookMapper.toBookEntity(book)));
	}
}
