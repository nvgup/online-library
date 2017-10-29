package com.onlinelibrary.web.controllers;

import com.onlinelibrary.dao.factory.DaoFactory;
import com.onlinelibrary.dao.factory.DaoFactoryImpl;
import com.onlinelibrary.model.Author;
import com.onlinelibrary.model.Book;
import com.onlinelibrary.model.Genre;
import com.onlinelibrary.service.BookService;
import com.onlinelibrary.service.GenreService;
import com.onlinelibrary.service.impl.BookServiceImpl;
import com.onlinelibrary.service.impl.GenreServiceImpl;
import com.onlinelibrary.validation.Validation;
import org.json.simple.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(
        urlPatterns = {"/Book", "/Editbook", "/Deletebook", "/Createbook"}
)
public class BooksManipulationController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private DaoFactory daoFactory;
    private BookService bookService;
    private GenreService genreService;

    @Override
    public void init() throws ServletException {
        daoFactory = new DaoFactoryImpl();
        bookService = new BookServiceImpl(daoFactory);
        genreService = new GenreServiceImpl(daoFactory);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookId = request.getParameter("bookId");
        String path = request.getRequestURI().substring(request.getContextPath().length());

        Book book = null;
        if (Validation.isPositiveInteger(bookId)) {
            book = bookService.getBookById(Long.valueOf(bookId));
        }
        if (book != null) {
            request.setAttribute("book", book);
            switch (path) {
                case "/Editbook":
                    editBookController(request, response);
                    break;
                case "/Deletebook":
                    deleteBookController(response, book);
                    break;
                default:
                    RequestDispatcher view = request.getRequestDispatcher("WEB-INF/book.jsp");
                    view.forward(request, response);
            }
        } else {
            errorBookController(request, response);
        }
    }

    private void editBookController(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Set<Genre> genres = genreService.getAllGenres();
        request.setAttribute("genres", genres);
        request.getRequestDispatcher("WEB-INF/editbook.jsp")
                .forward(request, response);
    }

    private void deleteBookController(HttpServletResponse response, Book book) throws ServletException, IOException {
        bookService.deleteBook(book);
        JSONObject json = new JSONObject();
        json.put("done", true);
        response.getWriter().write(json.toJSONString());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookId = request.getParameter("bookId");
        String path = request.getRequestURI().substring(request.getContextPath().length());
        Book book = null;

        switch (path) {
            case "/Editbook":
                if (Validation.isPositiveInteger(bookId)) {
                    book = bookService.getBookById(Long.valueOf(bookId));
                }
                break;
            case "/Createbook":
                book = new Book();
                break;
        }

        if (book == null) {
            errorBookController(request, response);
            return;
        }

        constructBook(book, request);
        bookService.saveOrUpdate(book);
        response.sendRedirect("Book?bookId=" + bookId);
    }

    private void constructBook(Book book, HttpServletRequest request) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String coverImgPath = request.getParameter("books-cover-path");
        String booksFilePath = request.getParameter("books-file-path");

        Set<Genre> newGenres = new HashSet<>();
        List<Author> newAuthors = new ArrayList<>();

        for (String reqParamName : Collections.list(request.getParameterNames())) {
            if (reqParamName.startsWith("genre_")) {
                newGenres.add(new Genre(Long.valueOf(reqParamName.substring(6)), request.getParameter(reqParamName)));
                continue;
            }
            if (reqParamName.startsWith("new-genre_")) {
                newGenres.add(new Genre(request.getParameter(reqParamName)));
                continue;
            }
            if (reqParamName.startsWith("author_")) {
                newAuthors.add(new Author(Long.valueOf(reqParamName.substring(7)), request.getParameter(reqParamName)));
                continue;
            }
            if (reqParamName.startsWith("new-author_")) {
                newAuthors.add(new Author(request.getParameter(reqParamName)));
            }
        }

        book.setName(name);
        book.setDescription(description);
        book.setCoverImgPath(coverImgPath);
        book.setBooksFilePath(booksFilePath);
        book.setGenres(newGenres);
        book.setAuthors(newAuthors);
    }

    private void errorBookController(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("message", "Error. Book was not found");
        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pageinfo.jsp");
        view.forward(request, response);
    }
}
