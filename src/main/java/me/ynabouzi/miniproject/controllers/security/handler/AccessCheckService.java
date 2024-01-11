package me.ynabouzi.miniproject.controllers.security.handler;

import me.ynabouzi.miniproject.bean.login.UserBean;

public class AccessCheckService {
    private PageRedirectionChain redirectionChain;

    public AccessCheckService() {
        redirectionChain = new PageRedirectionChain();
    }

    public String checkAccess(UserBean userBean, String currentPage) {
        return redirectionChain.processRequest(userBean, currentPage);
    }
}