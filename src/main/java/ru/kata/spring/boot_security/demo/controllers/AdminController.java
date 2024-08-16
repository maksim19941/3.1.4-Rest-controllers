package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.servise.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.servise.UserServiceImp;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserServiceImp userService;
    private final RoleServiceImpl roleService;

    @Autowired
    public AdminController(UserServiceImp userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("userList", userService.getListUser());
        return "userList";
    }

    @GetMapping(value = "/newUser")
    public String newUserPage(ModelMap model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "/newUser";
    }

    @PostMapping("/newUser")
    public String newUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/userInfoAdmin/{id}")
    public String showUserInfo(@ModelAttribute("id") long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "/userInfoAdmin";
    }

    @GetMapping(value = "/{id}/edit")
    public String editPage(@ModelAttribute("id") long id, ModelMap model) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "/editUser";
    }


    @PostMapping("/{id}/edit")
    public String editUser(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/admin";
    }


    @GetMapping("/{id}/deleteUser")
    public String deleteUser(@ModelAttribute("id") long id) {
       userService.delete(id);
        return "redirect:/admin";
    }


}
