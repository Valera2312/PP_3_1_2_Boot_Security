package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleRepo;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService,UserDetailsService {

    private final UserDao userDao;

    private final RoleRepo roleRepo;

    @Autowired
    UserServiceImpl(UserDao userDao, RoleRepo roleRepo) {

        this.roleRepo = roleRepo;
        this.userDao = userDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> listUsers() {
        return userDao.listUser();
    }

    @Override
    @Transactional()
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    @Override
    @Transactional
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    @Transactional
    public void editUser(User user) {
        userDao.editUser(user);
    }
    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }

    public String[] arrayRolesWithDeletedPrefixRole(String roles) {
        return roles.replace("ROLE_", "#ROLE_").split("#");
    }

    @Override
    @Transactional
    public void addRoles(String roles, User user) {

        String[] checkBoxArr =  arrayRolesWithDeletedPrefixRole(roles);
        for (String roleName:
                checkBoxArr) {
            user.addRole(roleRepo.findByName(roleName));
        }
    }
    @Override
    @Transactional
    public void addRolesForEditingMethod(String roles, User user,Long id) {

        if(roles.equals("false")) {
            user.setRoles(findById(id).getRoles());
        } else {
            String[] checkBoxArr = arrayRolesWithDeletedPrefixRole(roles);
            for (String roleName :
                    checkBoxArr) {
                user.addRole(roleRepo.findByName(roleName));
            }
        }
    }


    @Override
    @Transactional
    public void deleteRoles(String deleteAllRolesFlag,Long id) {
        if(deleteAllRolesFlag.equals("true")) {
            findById(id).setRolesEmpty();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = findByLogin(username);
       if(user == null) {
           throw new UsernameNotFoundException(String.format("User '%s' not found",username));
       }
       return new org.springframework.security.core.userdetails.User(user.getLogin(),
               user.getPassword(),mapRolesToAuthorities(user.getRoles()));

    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }
}

