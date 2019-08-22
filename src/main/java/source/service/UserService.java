package source.service;

import source.entity.User;

public interface UserService {
    User findUserByName(String name);
    Iterable<User> findAll();
    boolean saveUser(User user);
}
