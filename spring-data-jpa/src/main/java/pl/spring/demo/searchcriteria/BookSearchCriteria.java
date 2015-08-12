package pl.spring.demo.searchcriteria;

import pl.spring.demo.entity.AuthorEntity;

public class BookSearchCriteria {

	protected String title;
	protected AuthorEntity author;
	protected String libraryName;

	public BookSearchCriteria() {

	}

	public String getTitle() {
		return title;
	}

	public AuthorEntity getAuthor() {
		return author;
	}

	public String getLibraryName() {
		return libraryName;
	}

}
