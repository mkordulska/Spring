package pl.spring.demo.mock;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;

import pl.spring.demo.dao.BookDao;
import pl.spring.demo.dao.impl.BookDaoImpl;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.AuthorTo;

public class BookDaoImplMockTest {

	private BookDao bookDao;
	private Set<BookEntity> ALL_BOOKS;

	@Before
	public void setUp() {
		bookDao = new BookDaoImpl();
		ALL_BOOKS = new HashSet<>();
		addTestBooks();
		Whitebox.setInternalState(bookDao, "ALL_BOOKS", ALL_BOOKS);
	}

	private void addTestBooks() {
		ALL_BOOKS.add(new BookEntity(1L, "Opium w rosole", Arrays.asList(new AuthorTo(1L, "Hanna", "Ożogowska"))));
		ALL_BOOKS
				.add(new BookEntity(2L, "Awantura w Niekłaju", Arrays.asList(new AuthorTo(2L, "Edmund", "Niziurski"))));
		ALL_BOOKS.add(new BookEntity(3L, "Pan Samochodzik i Fantomas",
				Arrays.asList(new AuthorTo(3L, "Zbigniew", "Nienacki"))));
	}

	@Test
	public void testShouldFindAllBooks() {
		// when
		List<BookEntity> allBooks = bookDao.findAll();
		// then
		assertNotNull(allBooks);
		assertFalse(allBooks.isEmpty());
		assertEquals(3, allBooks.size());
	}

	@Test
	public void testShouldFindAllBooksByTitle() {
		// given
		final String title = "opium w";
		// when
		List<BookEntity> booksByTitle = bookDao.findBookByTitle(title);
		// then
		assertNotNull(booksByTitle);
		assertFalse(booksByTitle.isEmpty());
		assertEquals(1, booksByTitle.size());
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
		assertEquals(2, booksByAuthor.size());
	}

}
