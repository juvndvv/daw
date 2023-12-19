package edu.juana.dwsu3t3_2fornerjuanda.controllers;

import edu.juana.dwsu3t3_2fornerjuanda.entities.User;
import edu.juana.dwsu3t3_2fornerjuanda.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {

    Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String getIndexPage(@CookieValue(value = "username", required = false) String username,
                               Model model) {
        User currUser = userService.findByUsername(username);
        if (currUser == null)
            return "redirect:/login";

        model.addAttribute("user", currUser);

        return "index";
    }
}
