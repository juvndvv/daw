package edu.juana.dwsu3t3_2fornerjuanda.controllers;

import edu.juana.dwsu3t3_2fornerjuanda.entities.User;
import edu.juana.dwsu3t3_2fornerjuanda.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
    Logger logger = LoggerFactory.getLogger(RegisterController.class);

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute User newUser, HttpServletResponse response) {
        userService.addUser(newUser);

        Cookie usernameCookie = new Cookie("username", newUser.getNombre());
        usernameCookie.setMaxAge(-1); // sesión
        response.addCookie(usernameCookie);

        return "redirect:/";
    }
}
