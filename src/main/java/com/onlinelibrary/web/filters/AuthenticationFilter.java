package com.onlinelibrary.web.filters;

import com.onlinelibrary.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebFilter(
        urlPatterns = {"/Admin", "/Editbook", "/Deletebook"}
)
public class AuthenticationFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();

        Optional<User> sessionUser = Optional.ofNullable((User) session.getAttribute("currentSessionUser"));

        if (!sessionUser.isPresent()) {
            ((HttpServletResponse) response).sendRedirect(httpRequest.getContextPath() + "/login.jsp");
        } else if (!sessionUser.get().isAdmin()) {
            httpRequest.setAttribute("message", "Доступ заборонено");
            RequestDispatcher view = httpRequest.getRequestDispatcher("/WEB-INF/pageinfo.jsp");
            view.forward(httpRequest, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }

    public void destroy() {

    }
}
