package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import domain.User;
import service.UserService;

import javax.annotation.Resource;

public class UserServiceImpl implements UserService {

    private UserDao ud;

    @Override
    public User findUserByUserCode(User user) {
        User exitU = ud.findUserByUserCode(user.getUser_code());
        if (exitU==null){
            throw  new RuntimeException("用户不存在");

        }
        if (!exitU.getUser_password().equals(user.getUser_password())){
            throw  new RuntimeException("密码错误");
        }

        return exitU;
    }

    public void setUd(UserDao ud) {
        this.ud = ud;
    }
}
