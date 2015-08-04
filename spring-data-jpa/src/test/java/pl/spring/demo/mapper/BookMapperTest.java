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
	public void setUp() {
		bookMapper = new BookMapper();
	}

	@Test
	public void testShouldReturnBookTo() {
		// given
		BookEntity bookEntity = new BookEntity(1L, "title",
				Arrays.asList(new AuthorTo(1L, "firstName", "lastName"), new AuthorTo(2L, "firstName2", "lastName2")));
		// when
		BookTo bookTo = bookMapper.toBookTo(bookEntity);
		// then
		assertTrue(bookTo instanceof BookTo);
		assertEquals(1L, bookTo.getId().longValue());
		assertEquals("title", bookTo.getTitle());
	}

	@Test
	public void testShouldReturnBookEntity() {
		// given
		BookTo bookTo = new BookTo(1L, "title", "firstName lastName, firstName2 lastName2");
		// when
		BookEntity bookEntity = bookMapper.toBookEntity(bookTo);
		// then
		assertTrue(bookEntity instanceof BookEntity);
		assertEquals(1L, bookEntity.getId().longValue());
		assertEquals("title", bookEntity.getTitle());
		assertEquals(2, bookEntity.getAuthors().size());
	}

	@Test
	public void testShouldReturnBookToList() {
		// given
		List<BookEntity> bookEntityList = Arrays.asList(
				new BookEntity(1L, "title", Arrays.asList(new AuthorTo(1L, "firstName", "lastName"))),
				new BookEntity(2L, "title", Arrays.asList(new AuthorTo(2L, "firstName2", "lastName2"))));
		// when
		List<BookTo> bookToList = bookMapper.toListBookTo(bookEntityList);
		// then
		for (BookTo bookTo : bookToList) {
			assertTrue(bookTo instanceof BookTo);
		}
		assertEquals(2, bookToList.size());
	}

	@Test
	public void testShouldReturnBookEntityList() {
		// given
		List<BookTo> bookToList = Arrays.asList(new BookTo(1L, "title", "firstName lastName"),
				new BookTo(2L, "title", "firstName2 lastName2"));
		// when
		List<BookEntity> bookEntityList = bookMapper.toListBookEntity(bookToList);
		// then
		for (BookEntity bookEntity : bookEntityList) {
			assertTrue(bookEntity instanceof BookEntity);
		}
		assertEquals(2, bookEntityList.size());
	}

	@Test
	public void testShouldReturnNull() {
		assertNull(bookMapper.toBookEntity(null));
		assertNull(bookMapper.toBookTo(null));
		assertNull(bookMapper.toListBookEntity(null));
		assertNull(bookMapper.toListBookTo(null));
	}

}
