package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
public class RestUserController {


    private final UserService userService;
    @Autowired
    public RestUserController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = {"showUsers"}, method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<User> showAllUsers() {
        return userService.listUsers();
    }
    @RequestMapping(value = {"showCurrentUser"}, method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody User showCurrentUser(Principal principal) {
        return userService.findByLogin(principal.getName());
    }
    @GetMapping(value = {"admin/delete/{id}"},produces = "application/json")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
