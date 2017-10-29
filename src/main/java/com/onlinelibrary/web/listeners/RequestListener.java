package com.onlinelibrary.web.listeners;

import com.onlinelibrary.utils.JpaUtil;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class RequestListener implements ServletRequestListener {

    public void requestDestroyed(ServletRequestEvent event) {
        JpaUtil.closeEntityManager();
    }

    public void requestInitialized(ServletRequestEvent event) {
    }
}
