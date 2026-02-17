package org.silvachristian.searchfilms.controllers;

import org.silvachristian.searchfilms.entity.FilmDetails;
import org.silvachristian.searchfilms.repository.FilmRepository;
import org.silvachristian.searchfilms.services.FilmServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class FilmController {

    FilmServices filmServices;
    FilmRepository movieRepository;

    FilmController(FilmServices filmServices, FilmRepository movieRepository) {
        this.filmServices = filmServices;
        this.movieRepository = movieRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("movie", new FilmDetails());
        return "home";
    }

    @PostMapping("/home")
    public String postHome(@RequestParam String filmTitle, Model model) {
        FilmDetails movie = filmServices.findByTitle(filmTitle);
        movieRepository.save(movie);

        model.addAttribute("movie", movie);

        return "home";
    }

    @GetMapping("/favorites")
    public String favorites(Model model) {
        List<FilmDetails> movieFavorites = filmServices.searchAllFavoriteMovies();
        model.addAttribute("movies", movieFavorites);
        return "favorites";
    }
}
