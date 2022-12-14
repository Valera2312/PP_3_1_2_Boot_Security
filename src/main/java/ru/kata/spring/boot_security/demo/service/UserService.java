package ru.kata.spring.boot_security.demo.service;




import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {
    List<User> listUsers();
    void deleteUser(Long id);
    void addUser(User user);
    void editUser(User user);
    User findById(Long id);
    User findByLogin(String login);
    void deleteRoles(User user, String delete_all_roles, Long id);
    void addRolesForEdit(String[] roles, User user, Long id);
    void addRoles(String[] roles, User user);
}
