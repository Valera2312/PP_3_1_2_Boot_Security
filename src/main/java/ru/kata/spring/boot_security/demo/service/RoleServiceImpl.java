package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.RoleRepo;
import ru.kata.spring.boot_security.demo.models.Role;
@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    RoleRepo roleRepo;

    @Override
    public Role findByName(String name) {
        return roleRepo.findByName(name).orElse(null);
    }
}
