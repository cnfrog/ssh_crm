package dao;

import domain.User;

public interface UserDao extends BaseDao<User>{
    User findUserByUserCode(String user_code);
}
