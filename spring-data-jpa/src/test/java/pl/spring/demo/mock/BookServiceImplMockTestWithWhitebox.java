package pl.spring.demo.mock;
/**
 * @COPYRIGHT (C) 2015 Schenker AG
 *
 * All rights reserved
 */

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.mockito.internal.util.reflection.Whitebox;

import pl.spring.demo.dao.BookDao;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.mapper.BookMapper;
import pl.spring.demo.service.impl.BookServiceImpl;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookTo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * TODO The class BookServiceImplTest is supposed to be documented...
 *
 * @author AOTRZONS
 */
public class BookServiceImplMockTestWithWhitebox {

	@InjectMocks
	private BookServiceImpl bookService;
	@Mock
	private BookDao bookDao;
	private BookMapper bookMapper;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		bookMapper = new BookMapper();
		Whitebox.setInternalState(bookService, "bookMapper", bookMapper);
	}

	@Test
	public void testShouldFindAllBooks() {
		// given
		List<BookEntity> allBooks = new ArrayList<>();
		allBooks.add(new BookEntity(1L, "Romeo i Julia", Arrays.asList(new AuthorTo(1L, "Wiliam", "Szekspir"))));
		allBooks.add(new BookEntity(2L, "Opium w rosole", Arrays.asList(new AuthorTo(2L, "Hanna", "Ożogowska"))));
		allBooks.add(new BookEntity(3L, "Przygody Odyseusza", Arrays.asList(new AuthorTo(3L, "Jan", "Parandowski"))));
		Mockito.when(bookDao.findAll()).thenReturn(allBooks);
		// when
		List<BookTo> underTest = bookService.findAllBooks();
		// then
		Mockito.verify(bookDao).findAll();
		assertNotNull(underTest);
		assertFalse(underTest.isEmpty());
		assertEquals(3, underTest.size());
	}

	@Test
	public void testShouldFindAllBooksByTitle() {
		// given
		final String title = "W";
		List<BookEntity> booksByTitle = new ArrayList<>();
		booksByTitle.add(new BookEntity(2L, "Opium w rosole", Arrays.asList(new AuthorTo(2L, "Hanna", "Ożogowska"))));
		booksByTitle.add(new BookEntity(4L, "Awantura w Niekłaju", Arrays.asList(new AuthorTo(4L, "Edmund", "Niziurski"))));
		Mockito.when(bookDao.findBookByTitle(title)).thenReturn(booksByTitle);
		// when
		List<BookTo> underTest = bookService.findBooksByTitle(title);
		// then
		Mockito.verify(bookDao).findBookByTitle(title);
		assertNotNull(underTest);
		assertFalse(underTest.isEmpty());
		assertEquals(2, underTest.size());
	}

	@Test
	public void testShouldFindAllBooksByAuthor() {
		// given
		final String author = "NI";
		List<BookEntity> booksByAuthor = new ArrayList<>();
		booksByAuthor.add(new BookEntity(5L, "Pan Samochodzik i Fantomas",Arrays.asList(new AuthorTo(5L, "Zbigniew", "Nienacki"))));;
		booksByAuthor.add(new BookEntity(4L, "Awantura w Niekłaju", Arrays.asList(new AuthorTo(4L, "Edmund", "Niziurski"))));
		Mockito.when(bookDao.findBooksByAuthor(author)).thenReturn(booksByAuthor);
		// when
		List<BookTo> underTest = bookService.findBooksByAuthor(author);
		// then
		Mockito.verify(bookDao).findBooksByAuthor(author);
		assertNotNull(underTest);
		assertFalse(underTest.isEmpty());
		assertEquals(2, underTest.size());
	}

	@Test
	public void testShouldSaveBook() {
		// given
		BookTo bookToSave = new BookTo(null, "title", "firstName lastName");
		Mockito.when(bookDao.save(Mockito.any(BookEntity.class)))
				.thenReturn(new BookEntity(1L, "title", Arrays.asList(new AuthorTo(1L, "firstName", "lastName"))));
		// when
		BookTo underTest = bookService.saveBook(bookToSave);
		// then
		Mockito.verify(bookDao).save(Mockito.any(BookEntity.class));
		assertNotNull(underTest);
		assertEquals(1L, underTest.getId().longValue());
	}

	@Test(expected = BookNotNullIdException.class)
	public void testShouldThrowBookNotNullIdException() {
		// given
		BookTo bookToSave = new BookTo(null, "title", "firstName lastName");
		bookToSave.setId(22L);
		Mockito.when(bookDao.save(Mockito.any(BookEntity.class))).thenThrow(new BookNotNullIdException());
		// when
		bookService.saveBook(bookToSave);
		// then
		Mockito.verify(bookDao).save(Mockito.any(BookEntity.class));
		fail("test should throw BookNotNullIdException");
	}
}