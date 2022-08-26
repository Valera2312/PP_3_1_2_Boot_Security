package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;


@Controller
public class UserController  {


    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public UserController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String getUserAdmin(ModelMap model, Principal principal, Authentication authentication) {
        List<Role> listRoles = roleService.findAll();
        model.addAttribute("listRoles", listRoles);
        model.addAttribute("authorities", authentication.getAuthorities());
        model.addAttribute("users",userService.listUsers());
        model.addAttribute("user_name",principal.getName());
        return "admin_panel";
    }

    @GetMapping(value = "/user")
    public String getUser(ModelMap model, Principal principal,Authentication authentication) {
        User user =  userService.findByLogin(principal.getName());
        model.addAttribute("user",user);
        model.addAttribute("authorities", authentication.getAuthorities());
        model.addAttribute("user_name",principal.getName());
        return "user";
    }

    @GetMapping (path = "admin/delete/{id}" )
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
    @GetMapping(value = "/new" )
    public String addUser(Model model) {
        List<Role> listRoles = roleService.findAll();
        model.addAttribute("listRoles", listRoles);

        return "new";
    }

    @GetMapping(value = "/edit" )
    public String updateUser(@RequestParam Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user",user);
        List<Role> listRoles = roleService.findAll();
        model.addAttribute("listRoles", listRoles);
        return "update";
    }

    @GetMapping(value = "/denied" )
    public String updateUser() {
        return "denied";
    }

    @PostMapping(path = "admin/add" )
    public String addUser(@ModelAttribute("user") User user,
                          @RequestParam(name = "roleCheckbox",defaultValue = "false")String[] checkboxValueRoles) {
        userService.addRoles(checkboxValueRoles,user);
        userService.addUser(user);
        return "redirect:/";
    }
    @PostMapping(path = "admin/update")
    public String editUser( @ModelAttribute("user") User user, @RequestParam("id") Long id,
                            @RequestParam(name = "roleCheckbox",defaultValue = "false")String[] checkboxValueRoles,
                            @RequestParam(name = "delete_all_roles",defaultValue = "false")String delete_all_roles) {

        userService.deleteRoles(user,delete_all_roles,id);
        userService.addRoles(checkboxValueRoles,user);
        userService.editUser(user);
        return "redirect:/";
    }
    @ModelAttribute(value = "user")
    public User newUser()
    {
        return new User();
    }

}
