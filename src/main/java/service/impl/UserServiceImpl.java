package service.impl;

import dao.UserDao;
import domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.UserService;
import utils.MD5Utils;

import javax.annotation.Resource;

@Service("userService")
@Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.NEVER,readOnly = false)
public class UserServiceImpl implements UserService {

    @Resource(name = "userDao")
    private UserDao ud;

    @Override
    public User findUserByUserCode(User user) {
        User exitU = ud.findUserByUserCode(user.getUser_code());
        if (exitU == null) {
            throw new RuntimeException("用户不存在");

        }
        if (!exitU.getUser_password().equals(MD5Utils.md5(user.getUser_password()))) {
            throw new RuntimeException("密码错误");
        }
        return exitU;
    }

    @Override
    public void saveUser(User user) {
        //1调用dao根据注册的登录名获得用户对象
        User exitU = ud.findUserByUserCode(user.getUser_code());
        //2如果获得到User对象,用户名已经存在,抛出异常
        if (exitU != null) {
            throw new RuntimeException("用户名已存在");
        }
        //使用MD5对密码进行加密
        user.setUser_password(MD5Utils.md5(user.getUser_password()));
        //3调用Dao执行保存
        ud.save(user);
    }

    public void setUd(UserDao ud) {
        this.ud = ud;
    }
}
