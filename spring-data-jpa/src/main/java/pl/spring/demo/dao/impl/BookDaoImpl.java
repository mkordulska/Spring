package pl.spring.demo.dao.impl;

import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.annotation.SetId;
import pl.spring.demo.common.Sequence;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.AuthorTo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookDaoImpl implements BookDao {

	private final Set<BookEntity> ALL_BOOKS = new HashSet<>();

	@Autowired
	private Sequence sequence;

	public BookDaoImpl() {
		addTestBooks();
	}

	@Override
	public List<BookEntity> findAll() {
		return new ArrayList<>(ALL_BOOKS);
	}

	@Override
	public List<BookEntity> findBookByTitle(String title) {
		List<BookEntity> booksByTitle = new ArrayList<>();
		for (BookEntity book : ALL_BOOKS) {
			if (book.getTitle().regionMatches(true, 0, title, 0, title.length())) {
				booksByTitle.add(book);
			}
		}
		return booksByTitle;
	}

	@Override
	public List<BookEntity> findBooksByAuthor(String author) {
		List<BookEntity> booksByAuthor = new ArrayList<>();
		for (BookEntity book : ALL_BOOKS) {
			for (AuthorTo a : book.getAuthors()) {
				if (a.getFirstName().regionMatches(true, 0, author, 0, author.length())
						|| a.getLastName().regionMatches(true, 0, author, 0, author.length())) {
					booksByAuthor.add(book);
					break;
				}
			}
		}
		return booksByAuthor;
	}

	@Override
	@NullableId
	@SetId
	public BookEntity save(BookEntity book) {
		ALL_BOOKS.add(book);
		return book;
	}

	public long getNextId() {
		return sequence.nextValue(ALL_BOOKS);
	}

	private void addTestBooks() {
		ALL_BOOKS.add(new BookEntity(1L, "Romeo i Julia", Arrays.asList(new AuthorTo(1L, "Wiliam", "Szekspir"))));
		ALL_BOOKS.add(new BookEntity(2L, "Opium w rosole", Arrays.asList(new AuthorTo(2L, "Hanna", "Ożogowska"))));
		ALL_BOOKS.add(new BookEntity(3L, "Przygody Odyseusza", Arrays.asList(new AuthorTo(3L, "Jan", "Parandowski"))));
		ALL_BOOKS
				.add(new BookEntity(4L, "Awantura w Niekłaju", Arrays.asList(new AuthorTo(4L, "Edmund", "Niziurski"))));
		ALL_BOOKS.add(new BookEntity(5L, "Pan Samochodzik i Fantomas",
				Arrays.asList(new AuthorTo(5L, "Zbigniew", "Nienacki"))));
		ALL_BOOKS.add(new BookEntity(6L, "Zemsta", Arrays.asList(new AuthorTo(6L, "Aleksander", "Fredro"))));
	}
}
