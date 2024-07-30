package org.example.spring_mvc_hibernate_crud.controller;

import lombok.RequiredArgsConstructor;
import org.example.spring_mvc_hibernate_crud.entity.User;
import org.example.spring_mvc_hibernate_crud.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public String getAllUsers(ModelMap model) {
        model.addAttribute("users", userService.getAllUser());
        return "users";
    }

    @GetMapping("/add")
    public String addUser() {
        return "addUser";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/users/all";
    }

    @GetMapping("/update/{id}")
    public String getUpdateUserById(@PathVariable("id") int id, ModelMap model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "update";
    }

    @PostMapping("/update/{id}")
    public String updateUserById(@PathVariable("id") int id, @ModelAttribute("user") User user) {
        userService.updateUser(user, id);
        return "redirect:/users/all";
    }

    @GetMapping("/{id}")
    public String deleteUserById(@PathVariable("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/users/all";
    }
}
