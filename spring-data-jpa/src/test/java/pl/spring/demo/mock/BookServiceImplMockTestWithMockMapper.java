package pl.spring.demo.mock;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import pl.spring.demo.dao.BookDao;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.mapper.BookMapper;
import pl.spring.demo.service.impl.BookServiceImpl;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookTo;

public class BookServiceImplMockTestWithMockMapper {

	@InjectMocks
	private BookServiceImpl bookService;
	@Mock
	private BookDao bookDao;
	@Mock
	private BookMapper bookMapper;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testShouldFindAllBooks() {
		// given
		List<BookEntity> allBooks = new ArrayList<>();
		allBooks.add(new BookEntity(1L, "Romeo i Julia", Arrays.asList(new AuthorTo(1L, "Wiliam", "Szekspir"))));
		allBooks.add(new BookEntity(2L, "Opium w rosole", Arrays.asList(new AuthorTo(2L, "Hanna", "Ożogowska"))));
		allBooks.add(new BookEntity(3L, "Przygody Odyseusza", Arrays.asList(new AuthorTo(3L, "Jan", "Parandowski"))));
		Mockito.when(bookDao.findAll()).thenReturn(allBooks);
		Mockito.when(bookMapper.toListBookTo(allBooks)).thenCallRealMethod();
		// when
		List<BookTo> underTest = bookService.findAllBooks();
		// then
		Mockito.verify(bookDao).findAll();
		Mockito.verify(bookMapper).toListBookTo(allBooks);
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
		Mockito.when(bookMapper.toListBookTo(booksByTitle)).thenCallRealMethod();
		// when
		List<BookTo> underTest = bookService.findBooksByTitle(title);
		// then
		Mockito.verify(bookDao).findBookByTitle(title);
		Mockito.verify(bookMapper).toListBookTo(booksByTitle);
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
		Mockito.when(bookMapper.toListBookTo(booksByAuthor)).thenCallRealMethod();
		// when
		List<BookTo> underTest = bookService.findBooksByAuthor(author);
		// then
		Mockito.verify(bookDao).findBooksByAuthor(author);
		Mockito.verify(bookMapper).toListBookTo(booksByAuthor);
		assertNotNull(underTest);
		assertFalse(underTest.isEmpty());
		assertEquals(2, underTest.size());
	}

	@Test
	public void testShouldSaveBook() {
		// given
		BookTo bookToSave = new BookTo(null, "title", "firstName lastName");
		Mockito.when(bookDao.save(bookMapper.toBookEntity(bookToSave)))
				.thenReturn(new BookEntity(1L, "title", Arrays.asList(new AuthorTo(1L, "firstName", "lastName"))));
		// when
		bookService.saveBook(bookToSave);
		// then
		Mockito.verify(bookDao).save(bookMapper.toBookEntity(bookToSave));
	}

	@Test(expected = BookNotNullIdException.class)
	public void testShouldThrowBookNotNullIdException() {
		// given
		BookTo bookToSave = new BookTo(null, "title", "firstName lastName");
		bookToSave.setId(22L);
		Mockito.when(bookDao.save(bookMapper.toBookEntity(bookToSave))).thenThrow(new BookNotNullIdException());
		// when
		bookService.saveBook(bookToSave);
		// then
		Mockito.verify(bookDao).save(bookMapper.toBookEntity(bookToSave));
		fail("test should throw BookNotNullIdException");
	}
}
