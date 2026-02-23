package org.silvachristian.searchfilms.controllers;

import org.silvachristian.searchfilms.entity.UserEntity;
import org.silvachristian.searchfilms.services.LoginServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    LoginServices loginServices;

    LoginController(LoginServices loginServices) {
        this.loginServices = loginServices;
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new UserEntity());
        return "/userauth/login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new UserEntity());
        return "/userauth/signup";
    }

    @PostMapping("/signup")
    public String postSignup(@ModelAttribute("user") UserEntity userEntity) {
        if(loginServices.registerUser(userEntity))
            return "redirect:/movies/favorites";
        return "/userauth/signup";
    }

}
