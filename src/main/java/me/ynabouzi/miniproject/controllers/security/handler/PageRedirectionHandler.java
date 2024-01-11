package me.ynabouzi.miniproject.controllers.security.handler;

import me.ynabouzi.miniproject.bean.login.UserBean;

// Interface for handling redirection
public interface PageRedirectionHandler {
    String handleRedirection(UserBean userBean, String currentPage);
}
