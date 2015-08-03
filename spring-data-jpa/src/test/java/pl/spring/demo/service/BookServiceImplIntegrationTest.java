package pl.spring.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.BookTo;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class BookServiceImplIntegrationTest {

	@Autowired
	private BookService bookService;

	@Test
	public void testShouldFindAllBooks() {
		// when
		List<BookTo> allBooks = bookService.findAllBooks();
		// then
		assertNotNull(allBooks);
		assertFalse(allBooks.isEmpty());
	}

	@Test
	public void testShouldFindAllBooksByTitle() {
		// given
		final String title = "W";
		// when
		List<BookTo> booksByTitle = bookService.findBooksByTitle(title);
		// then
		assertNotNull(booksByTitle);
		assertFalse(booksByTitle.isEmpty());
	}

	@Test
	public void testShouldFindAllBooksByAuthor() {
		// given
		final String author = "NI";
		// when
		List<BookTo> booksByAuthor = bookService.findBooksByAuthor(author);
		// then
		assertNotNull(booksByAuthor);
		assertFalse(booksByAuthor.isEmpty());
	}
	
	@Test
	public void testShouldSaveBook(){
		// given
		final BookTo bookToSave = new BookTo(null, "title", "firstName lastName");
		//when
		BookTo underTest = bookService.saveBook(bookToSave);
		// then
		assertNotNull(bookToSave);
		assertNotNull(underTest.getId().longValue());
	}

	@Test(expected = BookNotNullIdException.class)
	public void testShouldThrowBookNotNullIdException() {
		// given
		final BookTo bookToSave = new BookTo(null, "title", "firstName lastName");
		bookToSave.setId(22L);
		// when
		bookService.saveBook(bookToSave);
		// then
		fail("test should throw BookNotNullIdException");
	}
}
