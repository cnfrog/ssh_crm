package service;

import domain.User;

public interface UserService {

    User findUserByUserCode(User user);
}
