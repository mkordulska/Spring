package pl.spring.demo.dao;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.common.Sequence;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.AuthorTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "BookDaoImplTest-context.xml")
public class BookDaoImplWithSpringContextTest {
	@Autowired
	private BookDao bookDao;
	@Autowired
	private Sequence sequence;

	@Test
	public void testShouldSaveBook() {
		// given
		BookEntity book = new BookEntity(null, "title", Arrays.asList(new AuthorTo(1L, "firstName", "lastName")));
		Mockito.when(sequence.nextValue(Mockito.anyCollection())).thenReturn(6L);
		// when
		BookEntity underTest = bookDao.save(book);
		// then
		Mockito.verify(sequence).nextValue(Mockito.anyCollection());
		assertNotNull(underTest);
		assertEquals(6L, underTest.getId().longValue());
	}

	@Test(expected = BookNotNullIdException.class)
	public void testShouldThrowBookNotNullIdException() {
		// given
		BookEntity book = new BookEntity(null, "title", Arrays.asList(new AuthorTo(1L, "firstName", "lastName")));
		book.setId(22L);
		// when
		bookDao.save(book);
		// then
		fail("test should throw BookNotNullIdException");
	}
}