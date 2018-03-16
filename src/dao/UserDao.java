package dao;

import domain.User;

public interface UserDao {
    User findUserByUserCode(String user_code);
}
