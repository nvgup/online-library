package com.onlinelibrary.service;

import com.onlinelibrary.model.Book;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();

    Book getBookById(Long id);

    List<Book> getBooksByName(String name);

    List<Book> getBooksByAuthorsName(String authorName);

    List<Book> getBooksByGenresName(String genreName);

    void updateBook(Book book);

    void deleteBook(Book book);

    void saveOrUpdate(Book book);
}
