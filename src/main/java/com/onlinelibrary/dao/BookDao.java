package com.onlinelibrary.dao;

import com.onlinelibrary.model.Book;

import java.util.List;

public interface BookDao extends GenericDao<Book, Long> {

    List<Book> getAllBooks();

    List<Book> getBooksByName(String name);

    List<Book> getBooksByAuthorsName(String authorName);

    List<Book> getBooksByGenresName(String name);
}
