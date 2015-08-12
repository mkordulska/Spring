package pl.spring.demo.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.EclipseLinkTemplates;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.hibernate.HibernateQuery;
import com.mysema.query.jpa.impl.JPAQuery;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.entity.QAuthorEntity;
import pl.spring.demo.entity.QBookEntity;
import pl.spring.demo.entity.QLibraryEntity;
import pl.spring.demo.repository.MyCustomBookRepository;
import pl.spring.demo.searchcriteria.BookSearchCriteria;

public class BookRepositoryImpl implements MyCustomBookRepository {

	@PersistenceContext(name = "hsql")
	private EntityManager entityManager;
	private BooleanBuilder restriction;

	@Override
	public List<BookEntity> findBooksByBookSearchCriteria(BookSearchCriteria bookSearchCriteria) {
		QBookEntity bookEntity = QBookEntity.bookEntity;
		JPQLQuery query = new JPAQuery(entityManager, EclipseLinkTemplates.DEFAULT).from(bookEntity);
		restriction = new BooleanBuilder();
		if (bookSearchCriteria.getTitle() != null) {
			restriction.and(bookEntity.title.startsWithIgnoreCase(bookSearchCriteria.getTitle()));
		}
		if (bookSearchCriteria.getAuthor() != null) {
			QAuthorEntity authorEntity = QAuthorEntity.authorEntity;
			query.join(bookEntity.authors, authorEntity);
			restriction.and(bookEntity.authors.contains(bookSearchCriteria.getAuthor()));
		}
		if (bookSearchCriteria.getLibraryName() != null) {
			QLibraryEntity libraryEntity = QLibraryEntity.libraryEntity;
			query.join(bookEntity.library, libraryEntity);
			restriction.and(bookEntity.library.name.startsWithIgnoreCase(bookSearchCriteria.getLibraryName()));
		}
		query.where(restriction);
		return query.list(bookEntity);
	}

}
