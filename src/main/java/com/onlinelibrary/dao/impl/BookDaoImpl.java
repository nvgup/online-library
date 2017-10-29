package com.onlinelibrary.dao.impl;

import com.onlinelibrary.dao.BookDao;
import com.onlinelibrary.model.Author;
import com.onlinelibrary.model.Book;
import com.onlinelibrary.model.Genre;
import com.onlinelibrary.utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class BookDaoImpl extends GenericDaoImpl<Book, Long> implements BookDao {

    @Override
    public List<Book> getAllBooks() {
        EntityManager entityManager = JpaUtil.getEntityManager();
        Query query = entityManager.createQuery("from Book");
        return query.getResultList();
    }

    @Override
    public List<Book> getBooksByName(String name) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        Query query = entityManager.createQuery("from Book where lower(name) like :namePattern");
        query.setParameter("namePattern", "%" + name.toLowerCase() + "%");
        return query.getResultList();
    }

    @Override
    public List<Book> getBooksByAuthorsName(String authorName) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Author> authorRoot = criteriaQuery.from(Author.class);
        criteriaQuery.where(criteriaBuilder.equal(criteriaBuilder.lower(authorRoot.get("name")), authorName.toLowerCase()));
        Join<Author, Book> answers = authorRoot.join("books");
        CriteriaQuery<Book> cq = criteriaQuery.select(answers);
        TypedQuery<Book> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<Book> getBooksByGenresName(String name) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Genre> genreRoot = criteriaQuery.from(Genre.class);
        criteriaQuery.where(criteriaBuilder.equal(criteriaBuilder.lower(genreRoot.get("name")), name.toLowerCase()));
        Join<Genre, Book> answers = genreRoot.join("books");
        CriteriaQuery<Book> cq = criteriaQuery.select(answers);
        TypedQuery<Book> query = entityManager.createQuery(cq);
        return query.getResultList();
    }
}
