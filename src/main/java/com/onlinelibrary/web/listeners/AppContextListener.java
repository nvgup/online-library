package com.onlinelibrary.web.listeners;

import com.onlinelibrary.dao.factory.DaoFactoryImpl;
import com.onlinelibrary.model.UserType;
import com.onlinelibrary.security.UserRole;
import com.onlinelibrary.service.UserTypeService;
import com.onlinelibrary.service.impl.UserTypeServiceImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebListener
public class AppContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {
        UserTypeService userTypeService = new UserTypeServiceImpl(new DaoFactoryImpl());
        List<String> userTypes = userTypeService.getAllUserType().stream()
                .map(UserType::getType).collect(Collectors.toList());

        Map<String, String> roles = new HashMap<>();
        for (UserRole userRole : UserRole.values()) {
            int userTypeInx = userTypes.indexOf(userRole.getValue());
            if (userTypeInx != -1) {
                roles.put(userRole.toString(), userTypes.get(userTypeInx));
            }
        }
        event.getServletContext().setAttribute("roles", roles);
    }

    public void contextDestroyed(ServletContextEvent event) {
    }
}
