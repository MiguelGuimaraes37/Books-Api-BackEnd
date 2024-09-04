package io.deli.home.services;

import io.deli.home.model.Book;

import java.util.List;

public interface BookService {
    Book get(Integer id);

    void delete(Book user);

    List<Book> list();

}
