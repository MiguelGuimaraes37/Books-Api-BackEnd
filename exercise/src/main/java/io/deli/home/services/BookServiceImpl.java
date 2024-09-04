package io.deli.home.services;


import io.deli.home.model.Book;
import io.deli.home.persistence.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {


    private BookDao bookDao;

    @Autowired
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public Book get(Integer id) {
        return bookDao.findById(id);
    }

    @Override
    @Transactional
    public void delete(Book book) {
        bookDao.delete(book);
    }

    @Override
    public List<Book> list() {
        return bookDao.findAll();
    }

}
