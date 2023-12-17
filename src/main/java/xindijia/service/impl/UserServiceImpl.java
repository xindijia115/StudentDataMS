package xindijia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.session.SqlSession;
import xindijia.entity.User;
import xindijia.exception.ServiceException;
import xindijia.mapper.UserMapper;
import xindijia.service.UserService;
import xindijia.utils.MybatisUtil;

/**
 * @author xia
 * @since 2023/12/17/15:43
 */
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public void login(String number, String password) {
        if ("".equals(number) || "".equals(password)) {
            throw new ServiceException("用户名或密码不为空!");
        }
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getNumber, number);
        User user = mapper.selectOne(userLambdaQueryWrapper);
        if (user == null) {
            throw  new ServiceException("用户不存在!");
        } else {
            if (!user.getPassword().equals(password)) {
                throw new ServiceException("密码错误!");
            }
            System.out.println("登录成功");
        }
    }
}
