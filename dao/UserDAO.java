package dao;

import model.User;

public interface UserDAO {
    User findByEmail(String email);
    void save(User user);
}
