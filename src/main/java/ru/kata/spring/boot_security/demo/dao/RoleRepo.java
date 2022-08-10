package ru.kata.spring.boot_security.demo.dao;

import org.springframework.data.repository.CrudRepository;
import ru.kata.spring.boot_security.demo.models.Role;

import java.util.Optional;

public interface RoleRepo extends CrudRepository<Role,Integer> {

    Optional<Role> findByName(String name);

}
