package pl.spring.demo.mapper;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookTo;

public class BookMapperTest {

	private BookMapper bookMapper;

	@Before
	public void setUpt() {
		bookMapper = new BookMapper();
	}

	@Test
	public void testShouldReturnBookTo() {
		// given
		BookEntity bookEntity = new BookEntity(1L, "title", Arrays.asList(new AuthorTo(1L, "firstName", "lastName")));
		// when
		BookTo bookTo = bookMapper.toBookTo(bookEntity);
		// then
		assertTrue(bookTo instanceof BookTo);
	}

	@Test
	public void testShouldReturnBookEntity() {
		// given
		BookTo bookTo = new BookTo(1L, "title", "firstName lastName");
		// when
		BookEntity bookEntity = bookMapper.toBookEntity(bookTo);
		// then
		assertTrue(bookEntity instanceof BookEntity);
	}

	@Test
	public void testShouldReturnBookToList() {
		// given
		List<BookEntity> bookEntityList = Arrays
				.asList(new BookEntity(1L, "title", Arrays.asList(new AuthorTo(1L, "firstName", "lastName"))));
		// when
		List<BookTo> bookToList = bookMapper.toListBookTo(bookEntityList);
		// then
		for (BookTo bookTo : bookToList) {
			assertTrue(bookTo instanceof BookTo);
		}
	}

	@Test
	public void testShouldReturnBookEntityList() {
		// given
		List<BookTo> bookToList = Arrays.asList(new BookTo(1L, "title", "firstName lastName"));
		// when
		List<BookEntity> bookEntityList = bookMapper.toListBookEntity(bookToList);
		// then
		for (BookEntity bookEntity : bookEntityList) {
			assertTrue(bookEntity instanceof BookEntity);
		}
	}
	
	@Test
	public void testShouldReturnNull(){
		assertNull(bookMapper.toBookEntity(null));
		assertNull(bookMapper.toBookTo(null));
		assertNull(bookMapper.toListBookEntity(null));
		assertNull(bookMapper.toListBookTo(null));
	}

}
