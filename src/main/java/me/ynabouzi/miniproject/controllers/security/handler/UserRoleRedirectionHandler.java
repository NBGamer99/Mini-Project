package me.ynabouzi.miniproject.controllers.security.handler;

import me.ynabouzi.miniproject.bean.login.UserBean;
import me.ynabouzi.miniproject.enums.Users;

// Redirect based on user role
public class UserRoleRedirectionHandler implements PageRedirectionHandler {
    @Override
    public String handleRedirection(UserBean userBean, String currentPage) {
        if (userBean.isLoggedIn()) {
            if ("/index.xhtml".equals(currentPage)) {
                // Redirect to respective dashboards based on user role
                if (userBean.getRole().equals(Users.ADMIN)) {
                    return "/admin/index.xhtml?faces-redirect=true";
                } else if (userBean.getRole().equals(Users.PROFESSOR)) {
                    return "/professor/index.xhtml?faces-redirect=true";
                }
            } else if (currentPage.startsWith("/admin/") && !userBean.getRole().equals(Users.ADMIN)) {
                // Prevent access to admin pages for non-admin users
                return "/error.xhtml?faces-redirect=true"; // Redirect to an error page or handle differently
            } else if (currentPage.startsWith("/professor/") && !userBean.getRole().equals(Users.PROFESSOR)) {
                // Prevent access to professor pages for non-professor users
                return "/error.xhtml?faces-redirect=true"; // Redirect to an error page or handle differently
            }
        }
        return null;
    }
}