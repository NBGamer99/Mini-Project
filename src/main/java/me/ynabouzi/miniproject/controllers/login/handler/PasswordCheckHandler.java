package me.ynabouzi.miniproject.controllers.login.handler;

import me.ynabouzi.miniproject.bean.login.UserBean;
import me.ynabouzi.miniproject.controllers.error.ErrorMessageController;
import me.ynabouzi.miniproject.dao.DAOImpl.UserEntityDAOImpl;

public class PasswordCheckHandler implements LoginHandler {
    private UserEntityDAOImpl userService;
    
    public PasswordCheckHandler(UserEntityDAOImpl userService) {
        this.userService = userService;
    }
    
    @Override
    public String handleLogin(UserBean userBean, ErrorMessageController errorMessageController) {
        // Implement password check logic
        // If password is invalid, set error message and return appropriate redirection
        return null;
    }
}