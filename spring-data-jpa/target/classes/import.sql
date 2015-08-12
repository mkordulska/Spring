insert into library (id, name) values (10, 'Biblioteka Wojewódzka');
insert into library (id, name) values (11, 'Biblioteka Miejska');

insert into book (id, title, library_id) values (1, 'Pierwsza książka', 10);
insert into book (id, title, library_id) values (2, 'Druga książka', 11);
insert into book (id, title, library_id) values (3, 'Trzecia książka', 11);

insert into author (id, first_name, last_name) values (7, 'Jan', 'Kowalski');
insert into author (id, first_name, last_name) values (8, 'Zbigniew', 'Nowak');
insert into author (id, first_name, last_name) values (9, 'Janusz', 'Jankowski');

insert into book_author(book_id, author_id) values (1, 7);
insert into book_author(book_id, author_id) values (2, 8);
insert into book_author(book_id, author_id) values (3, 9);