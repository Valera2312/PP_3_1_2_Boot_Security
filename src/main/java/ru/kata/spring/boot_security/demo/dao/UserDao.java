package ru.kata.spring.boot_security.demo.dao;


import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;


public interface UserDao {
    List<User> listUser();
    void deleteUser(Long id);
    void addUser(User user);
    void editUser(User user);
    User findById(Long id);

    User findByLogin(String login);
}
