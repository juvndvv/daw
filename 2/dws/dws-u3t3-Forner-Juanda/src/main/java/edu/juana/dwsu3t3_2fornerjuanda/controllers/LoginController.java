package edu.juana.dwsu3t3_2fornerjuanda.controllers;

import edu.juana.dwsu3t3_2fornerjuanda.entities.User;
import edu.juana.dwsu3t3_2fornerjuanda.models.LoginForm;
import edu.juana.dwsu3t3_2fornerjuanda.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String loginPage(@CookieValue(value = "username", required = false) String username,
                            Model model) {
        User currUser = userService.findByUsername(username);
        if (currUser != null) { // obliga a cerrar sesion para registrarte
            return "redirect:/";
        }

        model.addAttribute("loginUser", new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String validarInicio(HttpServletResponse response, @ModelAttribute LoginForm loginForm, Model model) {
        User user = userService.findByLoginForm(loginForm);

        if (user == null) {
            logger.info("Usuario y contraseña fallidos");
            model.addAttribute("mensaje", "Usuario y contraseñas incorrectos");
            model.addAttribute("loginUser", new LoginForm());
            return "login";
        }

        Cookie usernameCookie = new Cookie("username", loginForm.getUsername());
        usernameCookie.setMaxAge(-1);
        response.addCookie(usernameCookie);

        model.addAttribute("user", user);

        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(@CookieValue(value = "username", required = false) Cookie usernameCookie, HttpServletResponse response) {
        usernameCookie.setMaxAge(0);

        response.addCookie(usernameCookie);

        return "redirect:/";
    }
}
