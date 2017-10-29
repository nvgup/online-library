package com.onlinelibrary.web.controllers;

import com.onlinelibrary.dao.factory.DaoFactory;
import com.onlinelibrary.dao.factory.DaoFactoryImpl;
import com.onlinelibrary.model.User;
import com.onlinelibrary.service.UserService;
import com.onlinelibrary.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;


@WebServlet(
        urlPatterns = {"/Login", "/Logout"}
)
public class AuthenticationController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private DaoFactory daoFactory;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        daoFactory = new DaoFactoryImpl();
        userService = new UserServiceImpl(daoFactory);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("currentSessionUser");
        session.removeAttribute("favoritesList");
        response.sendRedirect("Books");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Optional<User> user = Optional.ofNullable(userService.getUserByEmailAndPassword(email, password));

        if (user.isPresent()) {
            HttpSession session = request.getSession();
            session.setAttribute("currentSessionUser", user);
            response.sendRedirect("Books");
        } else {
            request.setAttribute("isUserExist", false);
            RequestDispatcher view = request.getRequestDispatcher("login.jsp");
            view.forward(request, response);
        }
    }
}
