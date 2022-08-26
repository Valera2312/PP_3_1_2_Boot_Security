package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@RestController
public class RestUserController {


    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public RestUserController(UserService userService,RoleService roleService) {
        this.roleService = roleService;
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
    public @ResponseBody void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }


    @PostMapping(path = "admin/add",produces = "application/json" )
    public @ResponseBody void addUser(@ModelAttribute("user") User user,
                          @RequestParam(name = "roleCheckbox",defaultValue = "false")String checkboxValueRoles) {

        userService.addRoles(checkboxValueRoles,user);
        userService.addUser(user);
    }
    @PostMapping(path = "admin/edit")
    public void editUser( @ModelAttribute("user") User user, @RequestParam("id") Long id,
                            @RequestParam(name = "roleCheckbox",defaultValue = "false")String checkboxValueRoles,
                           @RequestParam(name = "deleteAllRolesFlag",defaultValue = "false")String deleteAllRolesFlag) {

       userService.addRoles(checkboxValueRoles, user);
       userService.deleteRoles(deleteAllRolesFlag, id);
       userService.editUser(user);
   }
}
