package com.onlinelibrary.service.impl;

import com.onlinelibrary.dao.BookDao;
import com.onlinelibrary.dao.factory.DaoFactory;
import com.onlinelibrary.model.Book;
import com.onlinelibrary.service.BookService;
import com.onlinelibrary.templates.DataAccessTemplate;
import com.onlinelibrary.templates.JpaTemplate;

import java.util.List;

public class BookServiceImpl implements BookService {

    private DaoFactory daoFactory;
    private BookDao bookDao;
    private DataAccessTemplate dataAccessTemplate;

    public BookServiceImpl(DaoFactory daoFactory) {
        this(daoFactory, new JpaTemplate());
    }

    public BookServiceImpl(DaoFactory daoFactory, DataAccessTemplate dataAccessTemplate) {
        this.daoFactory = daoFactory;
        this.dataAccessTemplate = dataAccessTemplate;
        bookDao = daoFactory.createBookDao();
    }

    @Override
    public List<Book> getAllBooks() {
        return dataAccessTemplate.findData(res -> bookDao.getAllBooks());
    }

    @Override
    public Book getBookById(Long id) {
        return dataAccessTemplate.findData(res -> bookDao.findByID(Book.class, id));
    }

    @Override
    public List<Book> getBooksByName(String name) {
        return dataAccessTemplate.findData(res -> bookDao.getBooksByName(name));
    }

    @Override
    public List<Book> getBooksByAuthorsName(String authorName) {
        return dataAccessTemplate.findData(res -> bookDao.getBooksByAuthorsName(authorName));
    }

    @Override
    public List<Book> getBooksByGenresName(String genreName) {
        return dataAccessTemplate.findData(res -> bookDao.getBooksByGenresName(genreName));
    }

    @Override
    public void updateBook(Book book) {
        dataAccessTemplate.executeOperation(() -> bookDao.update(book));
    }

    @Override
    public void deleteBook(Book book) {
        dataAccessTemplate.executeOperation(() -> bookDao.delete(book));
    }

    @Override
    public void saveOrUpdate(Book book) {
        dataAccessTemplate.executeOperation(() -> bookDao.save(book));
    }

    public void setDaoFactory(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
        bookDao = daoFactory.createBookDao();
    }
}
