package io.deli.home.persistence.dao;

import io.deli.home.model.Book;
import io.deli.home.persistence.BookDao;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Repository
public class JpaBookDao extends GenericJpaDao<Book> implements BookDao {

    public JpaBookDao() {
        super(Book.class);
    }

    @Override
    public Book findByISBN(String ISBN) {
        try {
            CriteriaQuery<Book> criteriaQuery = em.getCriteriaBuilder().createQuery(modelType);
            Root<Book> root = criteriaQuery.from(modelType);
            criteriaQuery.where(em.getCriteriaBuilder().equal(root.get("ISBN"), ISBN));
            return em.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException ignored) {
            return null;
        }
    }

    public Book findByISBNWithoutId(Integer id, String ISBN) {
        try {
            CriteriaQuery<Book> criteriaQuery = em.getCriteriaBuilder().createQuery(modelType);
            Root<Book> root = criteriaQuery.from(Book.class);
            Predicate notId = em.getCriteriaBuilder().notEqual(root.get("id"), id);
            Predicate equalISBN = em.getCriteriaBuilder().equal(root.get("ISBN"), ISBN);
            criteriaQuery.where(em.getCriteriaBuilder().and(notId, equalISBN));
            return em.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException ignored) {
            return null;
        }
    }
}
