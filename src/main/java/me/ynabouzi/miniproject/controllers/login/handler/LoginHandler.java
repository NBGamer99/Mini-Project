package me.ynabouzi.miniproject.controllers.login.handler;

import me.ynabouzi.miniproject.bean.login.UserBean;
import me.ynabouzi.miniproject.controllers.error.ErrorMessageController;

public interface LoginHandler {
    String handleLogin(UserBean userBean, ErrorMessageController errorMessageController);
}