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
			String authors = "";
			for (int i = 0; i < bookEntity.getAuthors().size(); i++) {
				if (i != bookEntity.getAuthors().size() - 1) {
					authors = authors + bookEntity.getAuthors().get(i).getFirstName() + " "
							+ bookEntity.getAuthors().get(i).getLastName() + ", ";
				} else {
					authors = authors + bookEntity.getAuthors().get(i).getFirstName() + " "
							+ bookEntity.getAuthors().get(i).getLastName();
				}
			}
			return new BookTo(bookEntity.getId(), bookEntity.getTitle(), authors);
		}
		return null;
	}

	public BookEntity toBookEntity(BookTo bookTo) {
		if (bookTo != null) {
			List<AuthorTo> authors = new ArrayList<>();
			for (String author : bookTo.getAuthors().split(",")) {
				authors.add(new AuthorTo(1L, author.split(" ")[0], author.split(" ")[1]));
			}
			return new BookEntity(bookTo.getId(), bookTo.getTitle(), authors);
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
}