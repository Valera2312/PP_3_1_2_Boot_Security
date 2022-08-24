package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@RestController
public class RestUserController {


    private final UserService userService;
    @Autowired
    public RestUserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = {"showUsers"}, method = RequestMethod.GET, produces = "application/json")

    public @ResponseBody List<User> show()
    {
        return userService.listUsers();
    }
}