package org.silvachristian.searchfilms.controllers;

import org.silvachristian.searchfilms.entity.FilmDetails;
import org.silvachristian.searchfilms.services.FilmServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FilmController {

    FilmServices filmServices;

    FilmController(FilmServices filmServices) {
        this.filmServices = filmServices;
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("movie", new FilmDetails());
        return "home";
    }

    @PostMapping("/home")
    public String postHome(@RequestParam String filmTitle, Model model) {
        FilmDetails movie = filmServices.findByTitle(filmTitle);

        model.addAttribute("movie", movie);

        return "home";
    }
}
