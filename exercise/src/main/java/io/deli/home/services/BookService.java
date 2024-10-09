package io.deli.home.services;

import io.deli.home.model.Book;

import java.util.List;

public interface BookService {
    Book get(Integer id);

    Book getBookByISBN(String ISBN);

    Book getBookWithoutId(Integer id, String ISBN);

    void delete(Integer id);

    List<Book> list();

    Book createOrUpdate(Book book);

}
