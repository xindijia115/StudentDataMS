package xindijia.controller;

import javafx.scene.control.Alert;
import xindijia.service.UserService;
import xindijia.service.impl.UserServiceImpl;

/**
 * @author xia
 * @since 2023/12/17/13:39
 */
public class LoginController extends BaseController{

    private UserService userService = new UserServiceImpl();

    public boolean login(String number, String password) throws Exception{
        try {
            userService.login(number, password);
            alert("成功提示", "登录成功!", null, Alert.AlertType.INFORMATION);
            return true;
        } catch (Exception e) {
            String message = e.getMessage();
            alert("错误提示", message, null, Alert.AlertType.ERROR);
        }
        return false;
    }
}
