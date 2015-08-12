package pl.spring.demo.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.entity.LibraryEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonRepositoryTest-context.xml")
public class LibraryRepositoryTest {

    @Autowired
    private LibraryRepository libraryRepository;
    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testShouldFindLibraryByName() {
        // given
        final String libraryName = "bibl";
        // when
        List<LibraryEntity> libraryEntity = libraryRepository.findLibraryByName(libraryName);
        // then
        assertNotNull(libraryEntity);
        assertFalse(libraryEntity.isEmpty());
    }
    
    @Test
    public void testShouldDeleteLibraryWithBooks() {
		// given
		final Long libraryId = 10L;
		List<BookEntity> bookEntity = bookRepository.findAll();
		// when
		libraryRepository.delete(libraryId);
		List<BookEntity> bookEntityAfterDelete = bookRepository.findAll();
		// then
		assertNotEquals(bookEntity.size(), bookEntityAfterDelete.size());
    }
}
