package com.onlinelibrary.web.controllers;

import com.onlinelibrary.dao.factory.DaoFactory;
import com.onlinelibrary.dao.factory.DaoFactoryImpl;
import com.onlinelibrary.model.Book;
import com.onlinelibrary.service.BookService;
import com.onlinelibrary.service.impl.BookServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/Books")
public class BooksController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private DaoFactory daoFactory;
    private BookService bookService;

    @Override
    public void init() throws ServletException {
        daoFactory = new DaoFactoryImpl();
        bookService = new BookServiceImpl(daoFactory);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchQuery = request.getParameter("searchQuery");
        String searchBy = request.getParameter("searchBy");

        List<Book> books;
        switch (searchBy != null ? searchBy : "") {
            case "name":
                books = bookService.getBooksByName(searchQuery);
                break;
            case "author":
                books = bookService.getBooksByAuthorsName(searchQuery);
                break;
            case "genre":
                books = bookService.getBooksByGenresName(searchQuery);
                break;
            default:
                books = bookService.getAllBooks();
        }
        request.setAttribute("books", books);
        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/books.jsp");
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
