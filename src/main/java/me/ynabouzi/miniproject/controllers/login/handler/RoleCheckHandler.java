package me.ynabouzi.miniproject.controllers.login.handler;

import me.ynabouzi.miniproject.bean.login.UserBean;
import me.ynabouzi.miniproject.controllers.error.ErrorMessageController;

public class RoleCheckHandler implements LoginHandler {
    private UserBean userBean;
    
    public RoleCheckHandler(UserBean userBean) {
        this.userBean = userBean;
    }
    
    @Override
    public String handleLogin(UserBean userBean, ErrorMessageController errorMessageController) {
        // Implement role check logic and perform login actions
        // Set userBean properties based on role and return redirection
        return null;
    }
}