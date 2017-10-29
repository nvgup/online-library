package com.onlinelibrary.web.controllers;

import com.onlinelibrary.dao.factory.DaoFactory;
import com.onlinelibrary.dao.factory.DaoFactoryImpl;
import com.onlinelibrary.model.User;
import com.onlinelibrary.model.UserType;
import com.onlinelibrary.security.UserRole;
import com.onlinelibrary.service.UserService;
import com.onlinelibrary.service.UserTypeService;
import com.onlinelibrary.service.impl.UserServiceImpl;
import com.onlinelibrary.service.impl.UserTypeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/Registration")
public class RegistrationController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private DaoFactory daoFactory;
    private UserService userService;
    private UserTypeService userTypeService;

    @Override
    public void init() throws ServletException {
        daoFactory = new DaoFactoryImpl();
        userService = new UserServiceImpl(daoFactory);
        userTypeService = new UserTypeServiceImpl(daoFactory);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = saveNewUser(request);
        request.getSession().setAttribute("currentSessionUser", user);
        response.sendRedirect("Books");
    }

    private User saveNewUser(HttpServletRequest request) {
        User user = new User();
        UserType userType = userTypeService.getUserTypeByName(UserRole.COMMON_USER.getValue());

        user.setEmail(request.getParameter("email"));
        user.setName(request.getParameter("name"));
        user.setPassword(request.getParameter("password"));
        user.setUserType(userType);
        userService.createNewUser(user);
        return user;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
