package pl.spring.demo.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.entity.LibraryEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonDaoTest-context.xml")
public class LibraryDaoImplTest {

	@Autowired
	LibraryDao libraryDao;

    @Test
    public void testShouldFindLibraryByName() {
        // given
        final String libraryName = "bibl";
        // when
        List<LibraryEntity> libraryEntity = libraryDao.findLibrariesByName(libraryName);
        // then
        assertNotNull(libraryEntity);
        assertFalse(libraryEntity.isEmpty());
    }

}
