package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;


@Controller
public class UserController  {

    @Autowired
    RoleService roleService;

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/admin")
    public String getUserAdmin(ModelMap model,Principal principal) {
        model.addAttribute("users",userService.listUsers());
        return "admin_panel";
    }

    @GetMapping(value = "/user")
    public String getUser(ModelMap model, Principal principal) {
        User user =  userService.findByLogin(principal.getName());
        model.addAttribute("user",user);
        return "user";
    }

    @GetMapping(value = "admin/delete/{id}" )
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
    @GetMapping(value = "/new" )
    public String addUser() {
        return "new";
    }

    @GetMapping(value = "/edit/{id}" )
    public String updateUser(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user",user);
        return "update";
    }

    @PostMapping(path = "admin/add" )
    public String addUser(@ModelAttribute("user") User user, @RequestParam("role_name") String role) {
        user.addRole(roleService.findByName(role));
        userService.addUser(user);
        return "redirect:/admin";
    }
    @PostMapping(path = "admin/update" )
    public String editUser( @ModelAttribute("user") User user, @RequestParam("id") Long id) {

        userService.editUser(user);
        return "redirect:/admin";
    }
    @ModelAttribute(value = "user")
    public User newUser()
    {
        return new User();
    }

}
