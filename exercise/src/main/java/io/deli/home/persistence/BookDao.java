package io.deli.home.persistence;

import io.deli.home.model.Book;

public interface BookDao extends Dao<Book>{
    Book findByISBN(String ISBN);
    Book findByISBNWithoutId(Integer id, String ISBN);
}
