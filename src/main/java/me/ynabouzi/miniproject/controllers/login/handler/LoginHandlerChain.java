package me.ynabouzi.miniproject.controllers.login.handler;

import me.ynabouzi.miniproject.bean.login.UserBean;
import me.ynabouzi.miniproject.controllers.error.ErrorMessageController;
import me.ynabouzi.miniproject.dao.DAOImpl.UserEntityDAOImpl;

import java.util.ArrayList;
import java.util.List;

public class LoginHandlerChain {
    private List<LoginHandler> handlers;
    
    public LoginHandlerChain(UserEntityDAOImpl userService, UserBean userBean, ErrorMessageController errorMessageController) {
        handlers = new ArrayList<>();
        handlers.add(new UserExistsHandler(userService));
        handlers.add(new PasswordCheckHandler(userService));
        handlers.add(new RoleCheckHandler(userBean));
    }
    
    public String processLogin(UserBean userBean, ErrorMessageController errorMessageController) {
        for (LoginHandler handler : handlers) {
            String redirection = handler.handleLogin(userBean, errorMessageController);
            if (redirection != null) {
                return redirection;
            }
        }
        return null;
    }
}