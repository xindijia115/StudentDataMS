package xindijia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xindijia.entity.User;

/**
 * @author xia
 * @since 2023/12/17/15:43
 */
public interface UserService extends IService<User> {
    void login(String number, String password);
}
