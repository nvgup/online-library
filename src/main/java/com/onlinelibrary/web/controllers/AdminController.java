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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/Admin")
public class AdminController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private DaoFactory daoFactory;
    private GenreService genreService;
    private BookService bookService;

    @Override
    public void init() throws ServletException {
        daoFactory = new DaoFactoryImpl();
        genreService = new GenreServiceImpl(daoFactory);
        bookService = new BookServiceImpl(daoFactory);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Set<Genre> genres = genreService.getAllGenres();
        request.setAttribute("genres", genres);
        request.getRequestDispatcher("WEB-INF/admin.jsp")
                .forward(request, response);
    }
}
