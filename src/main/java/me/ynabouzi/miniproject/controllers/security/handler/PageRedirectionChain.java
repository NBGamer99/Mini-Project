package me.ynabouzi.miniproject.controllers.security.handler;

import me.ynabouzi.miniproject.bean.login.UserBean;

import java.util.ArrayList;
import java.util.List;

// Chain of responsibility implementation
public class PageRedirectionChain {
    private List<PageRedirectionHandler> handlers;

    public PageRedirectionChain() {
        handlers = new ArrayList<>();
        handlers.add(new RedirectIfNotLoggedInHandler());
        handlers.add(new UserRoleRedirectionHandler());
        // Add more handlers if needed
    }

    public String processRequest(UserBean userBean, String currentPage) {
        for (PageRedirectionHandler handler : handlers) {
            String redirection = handler.handleRedirection(userBean, currentPage);
            if (redirection != null) {
                return redirection;
            }
        }
        return null;
    }
}