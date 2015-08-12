package pl.spring.demo.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.entity.AuthorEntity;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.searchcriteria.BookSearchCriteria;
import pl.spring.demo.searchcriteria.BookSearchCriteriaBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonRepositoryTest-context.xml")
public class BookRepositoryImplTest {
	@Autowired
	private MyCustomBookRepository bookRepositoryImpl;

	@Test
	public void testShouldFind2BooksFor1Criteria() {
		BookSearchCriteria bookSearchCriteria = BookSearchCriteriaBuilder.aBookSearchCriteria().withLibraryName("biblioteka m").build();
		List<BookEntity> books = bookRepositoryImpl.findBooksByBookSearchCriteria(bookSearchCriteria);	
		assertNotNull(books);
		assertFalse(books.isEmpty());
		assertEquals(2, books.size());
	}
	
	@Test
	public void testShouldFind1BookFor2Criterias() {
		BookSearchCriteria bookSearchCriteria = BookSearchCriteriaBuilder.aBookSearchCriteria().withTitle("d").withLibraryName("biblioteka m").build();
		List<BookEntity> books = bookRepositoryImpl.findBooksByBookSearchCriteria(bookSearchCriteria);	
		assertNotNull(books);
		assertFalse(books.isEmpty());
		assertEquals(1, books.size());
	}
	
	@Test
	public void testShouldFind1BookForAllCriterias() {
		AuthorEntity author = new AuthorEntity(8L, "Zbigniew", "Nowak");
		BookSearchCriteria bookSearchCriteria = BookSearchCriteriaBuilder.aBookSearchCriteria().withTitle("d").withAuthor(author).withLibraryName("biblioteka m").build();
		List<BookEntity> books = bookRepositoryImpl.findBooksByBookSearchCriteria(bookSearchCriteria);	
		assertNotNull(books);
		assertFalse(books.isEmpty());
		assertEquals(1, books.size());
	}
	@Test
	public void testShouldFindAllBooksForNoCriteria() {
		BookSearchCriteria bookSearchCriteria = BookSearchCriteriaBuilder.aBookSearchCriteria().build();
		List<BookEntity> books = bookRepositoryImpl.findBooksByBookSearchCriteria(bookSearchCriteria);	
		assertNotNull(books);
		assertFalse(books.isEmpty());
		assertEquals(3, books.size());
	}

}
