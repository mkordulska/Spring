package pl.spring.demo.mock;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pl.spring.demo.dao.BookDao;
import pl.spring.demo.dao.impl.BookDaoImpl;
import pl.spring.demo.entity.BookEntity;

public class BookDaoImplMockTest {
	
	private BookDao bookDao;
	
	@Before
	public void setUp(){
		bookDao = new BookDaoImpl();
	}

	@Test
	public void testShouldFindAllBooks() {
		// when
		List<BookEntity> allBooks = bookDao.findAll();
		// then
		assertNotNull(allBooks);
		assertFalse(allBooks.isEmpty());
		assertEquals(6, allBooks.size());
	}
	
	@Test
	public void testShouldFindAllBooksByTitle() {
		// given
		final String title = "W";
		// when
		List<BookEntity> booksByTitle = bookDao.findBookByTitle(title);
		// then
		assertNotNull(booksByTitle);
		assertFalse(booksByTitle.isEmpty());
	}
	
	@Test
	public void testShouldFindAllBooksByAuthor() {
		// given
		final String author = "NI";
		// when
		List<BookEntity> booksByAuthor = bookDao.findBooksByAuthor(author);
		// then
		assertNotNull(booksByAuthor);
		assertFalse(booksByAuthor.isEmpty());
	}

}
