package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;


import java.security.Principal;
import java.util.List;


@Controller
public class UserController  {

    private final RoleService roleService;


    @Autowired
    public UserController(RoleService roleService) {
        this.roleService = roleService;

    }

    @GetMapping(value = "/")
    public String getUsersAdmin(ModelMap model, Principal principal, Authentication authentication) {
        List<Role> listRoles = roleService.findAll();
        model.addAttribute("listRoles", listRoles);
        model.addAttribute("authorities", authentication.getAuthorities());
        model.addAttribute("user_name",principal.getName());
        return "admin_panel";
    }

    @GetMapping(value = "/user")
    public String getUser(ModelMap model, Principal principal,Authentication authentication) {
        model.addAttribute("authorities", authentication.getAuthorities());
        model.addAttribute("user_name",principal.getName());
        return "user";
    }



    @GetMapping(value = "/denied" )
    public String return403() {
        return "denied";
    }


    @ModelAttribute(value = "user")
    public User newUser()
    {
        return new User();
    }

}
