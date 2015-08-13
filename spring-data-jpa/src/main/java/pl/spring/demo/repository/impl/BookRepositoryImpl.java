package pl.spring.demo.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.entity.QBookEntity;
import pl.spring.demo.repository.MyCustomBookRepository;
import pl.spring.demo.searchcriteria.BookSearchCriteria;

public class BookRepositoryImpl implements MyCustomBookRepository {

	@PersistenceContext(name = "hsql")
	private EntityManager entityManager;

	@Override
	public List<BookEntity> findBooksByBookSearchCriteria(BookSearchCriteria bookSearchCriteria) {
		QBookEntity bookEntity = QBookEntity.bookEntity;
		JPQLQuery query = new JPAQuery(entityManager).from(bookEntity);
		BooleanBuilder restriction = new BooleanBuilder();
		if (bookSearchCriteria.getTitle() != null) {
			restriction.and(bookEntity.title.startsWithIgnoreCase(bookSearchCriteria.getTitle()));
		}
		if (bookSearchCriteria.getAuthor() != null) {
			restriction.and(bookEntity.authors.any().firstName.startsWithIgnoreCase(bookSearchCriteria.getAuthor()).or(bookEntity.authors.any().lastName.startsWithIgnoreCase(bookSearchCriteria.getAuthor())));
		}
		if (bookSearchCriteria.getLibraryName() != null) {
			restriction.and(bookEntity.library.name.startsWithIgnoreCase(bookSearchCriteria.getLibraryName()));
		}
		query.where(restriction);
		return query.list(bookEntity);
	}

}
