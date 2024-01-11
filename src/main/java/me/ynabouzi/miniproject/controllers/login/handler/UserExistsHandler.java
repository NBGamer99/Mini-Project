package me.ynabouzi.miniproject.controllers.login.handler;

import me.ynabouzi.miniproject.bean.login.UserBean;
import me.ynabouzi.miniproject.controllers.error.ErrorMessageController;
import me.ynabouzi.miniproject.dao.DAOImpl.UserEntityDAOImpl;

// Check if the user exists
public class UserExistsHandler implements LoginHandler {
    private UserEntityDAOImpl userService;
    
    public UserExistsHandler(UserEntityDAOImpl userService) {
        this.userService = userService;
    }
    
    @Override
    public String handleLogin(UserBean userBean, ErrorMessageController errorMessageController) {
        // Implement user existence check logic
        // If user does not exist, set error message and return appropriate redirection
        return null;
    }
}