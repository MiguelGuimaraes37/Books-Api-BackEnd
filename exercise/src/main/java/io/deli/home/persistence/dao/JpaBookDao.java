package io.deli.home.persistence.dao;

import io.deli.home.model.Book;
import io.deli.home.persistence.BookDao;
import org.springframework.stereotype.Repository;

@Repository
public class JpaBookDao extends GenericJpaDao<Book> implements BookDao {

    public JpaBookDao() {
        super(Book.class);
    }

}
