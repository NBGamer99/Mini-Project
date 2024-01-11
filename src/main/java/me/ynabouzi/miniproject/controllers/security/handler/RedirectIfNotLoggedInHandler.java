package me.ynabouzi.miniproject.controllers.security.handler;

import me.ynabouzi.miniproject.bean.login.UserBean;

// Redirect to login if not logged in
public class RedirectIfNotLoggedInHandler implements PageRedirectionHandler {
    @Override
    public String handleRedirection(UserBean userBean, String currentPage) {
        if (!userBean.isLoggedIn() && !"/index.xhtml".equals(currentPage)) {
            return "/index.xhtml?faces-redirect=true"; // Redirect to login if not logged in
        }
        return null;
    }
}