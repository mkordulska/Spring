package pl.spring.demo.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.searchcriteria.BookSearchCriteria;
import pl.spring.demo.searchcriteria.BookSearchCriteriaBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonRepositoryTest-context.xml")
public class BookRepositoryImplTest {
	@Autowired
	private MyCustomBookRepository bookRepositoryImpl;

	@Test
	public void testShouldFindAllBooksForNoCriteria() {
		BookSearchCriteria bookSearchCriteria = BookSearchCriteriaBuilder.aBookSearchCriteria().build();
		List<BookEntity> books = bookRepositoryImpl.findBooksByBookSearchCriteria(bookSearchCriteria);
		assertNotNull(books);
		assertFalse(books.isEmpty());
		assertEquals(3, books.size());
	}

	@Test
	public void testShouldFind1BookFor1CriteriaTitle() {
		BookSearchCriteria bookSearchCriteria = BookSearchCriteriaBuilder.aBookSearchCriteria().withTitle("d").build();
		List<BookEntity> books = bookRepositoryImpl.findBooksByBookSearchCriteria(bookSearchCriteria);
		assertNotNull(books);
		assertFalse(books.isEmpty());
		assertEquals(1, books.size());
	}

	@Test
	public void testShouldFind2BooksFor1CriteriaAuthor() {
		BookSearchCriteria bookSearchCriteria = BookSearchCriteriaBuilder.aBookSearchCriteria().withAuthor("jan")
				.build();
		List<BookEntity> books = bookRepositoryImpl.findBooksByBookSearchCriteria(bookSearchCriteria);
		assertNotNull(books);
		assertFalse(books.isEmpty());
		assertEquals(2, books.size());
	}

	@Test
	public void testShouldFind2BooksFor1CriteriaLibraryName() {
		BookSearchCriteria bookSearchCriteria = BookSearchCriteriaBuilder.aBookSearchCriteria()
				.withLibraryName("biblioteka m").build();
		List<BookEntity> books = bookRepositoryImpl.findBooksByBookSearchCriteria(bookSearchCriteria);
		assertNotNull(books);
		assertFalse(books.isEmpty());
		assertEquals(2, books.size());
	}

	@Test
	public void testShouldFind1BookFor2CriteriasTitleAndAuthor() {
		BookSearchCriteria bookSearchCriteria = BookSearchCriteriaBuilder.aBookSearchCriteria().withTitle("d")
				.withAuthor("now").build();
		List<BookEntity> books = bookRepositoryImpl.findBooksByBookSearchCriteria(bookSearchCriteria);
		assertNotNull(books);
		assertFalse(books.isEmpty());
		assertEquals(1, books.size());
	}

	@Test
	public void testShouldFind1BookFor2CriteriasTitleAndLibraryName() {
		BookSearchCriteria bookSearchCriteria = BookSearchCriteriaBuilder.aBookSearchCriteria().withTitle("d")
				.withLibraryName("biblioteka m").build();
		List<BookEntity> books = bookRepositoryImpl.findBooksByBookSearchCriteria(bookSearchCriteria);
		assertNotNull(books);
		assertFalse(books.isEmpty());
		assertEquals(1, books.size());
	}

	@Test
	public void testShouldNotFindAnythingFor2CriteriasAuthorAndLibraryName() {
		BookSearchCriteria bookSearchCriteria = BookSearchCriteriaBuilder.aBookSearchCriteria().withAuthor("kow")
				.withLibraryName("biblioteka m").build();
		List<BookEntity> books = bookRepositoryImpl.findBooksByBookSearchCriteria(bookSearchCriteria);
		assertTrue(books.isEmpty());
	}

	@Test
	public void testShouldFind1BookForAllCriterias() {
		BookSearchCriteria bookSearchCriteria = BookSearchCriteriaBuilder.aBookSearchCriteria().withTitle("d")
				.withAuthor("now").withLibraryName("biblioteka m").build();
		List<BookEntity> books = bookRepositoryImpl.findBooksByBookSearchCriteria(bookSearchCriteria);
		assertNotNull(books);
		assertFalse(books.isEmpty());
		assertEquals(1, books.size());
	}

}
