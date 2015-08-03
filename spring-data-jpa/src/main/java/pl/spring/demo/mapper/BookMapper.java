package pl.spring.demo.mapper;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookTo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class BookMapper {

	public BookTo toBookTo(BookEntity bookEntity) {
		if (bookEntity != null) {
			return new BookTo(bookEntity.getId(), bookEntity.getTitle(), authorsInBookTo(bookEntity.getAuthors()));
		}
		return null;
	}

	public BookEntity toBookEntity(BookTo bookTo) {
		if (bookTo != null) {
			return new BookEntity(bookTo.getId(), bookTo.getTitle(), authorsInBookEntity(bookTo.getAuthors()));
		}
		return null;
	}

	public List<BookEntity> toListBookEntity(List<BookTo> bookTo) {
		if (bookTo != null) {
			List<BookEntity> bookEntityList = new ArrayList<>();
			for (BookTo bt : bookTo) {
				bookEntityList.add(toBookEntity(bt));
			}
			return bookEntityList;
		}
		return null;
	}

	public List<BookTo> toListBookTo(List<BookEntity> bookEntity) {
		if (bookEntity != null) {
			List<BookTo> bookToList = new ArrayList<>();
			for (BookEntity be : bookEntity) {
				bookToList.add(toBookTo(be));
			}
			return bookToList;
		}
		return null;
	}
	
	private String authorsInBookTo(List<AuthorTo> authors){
		StringBuilder authorsSb = new StringBuilder();
		for (int i = 0; i < authors.size(); i++) {
			if (i != authors.size() - 1) {
				authorsSb.append(authors.get(i).getFirstName() + " "
						+ authors.get(i).getLastName() + ", ");
			} else {
				authorsSb.append(authors.get(i).getFirstName() + " "
						+ authors.get(i).getLastName());
			}
		}
		return authorsSb.toString();
	}
	
	private List<AuthorTo> authorsInBookEntity(String authors){
		List<AuthorTo> authorsList = new ArrayList<>();
		for (String author : authors.split(",")) {
			authorsList.add(new AuthorTo(1L, author.split(" ")[0], author.split(" ")[1]));
		}
		return authorsList;
	}
}