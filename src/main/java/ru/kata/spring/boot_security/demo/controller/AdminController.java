package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserServiceImp userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping
    public String showAllPanel(Model model) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("currentUser", principal);
        model.addAttribute("allUsers", userService.getListUser());
        model.addAttribute("addUser", new User());
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "/showAdminPanel";
    }


    @PostMapping("/newUser")
    public String newUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/infoAdmin")
    public String showUserInfo(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("currentUser", user);
        return "showUserInfo_Admin";
    }


    @PostMapping("/{id}/edit")
    public String editUser(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/admin";
    }


    @PostMapping("/{id}/deleteUser")
    public String deleteUser(@RequestParam("id") long id) {
        userService.delete(id);
        return "redirect:/admin";
    }


}
