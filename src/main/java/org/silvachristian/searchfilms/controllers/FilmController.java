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

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("movie", new FilmDetails());
        return "home";
    }

    @PostMapping("/home")
    public String postHome(@RequestParam String filmTitleGenre, Model model) {
        FilmDetails movie = filmServices.findByTitle(filmTitleGenre);
        movieRepository.save(movie);

        model.addAttribute("movie", movie);

        return "home";
    }

    @GetMapping("/favorites")
    public String favorites(Model model) {
        List<FilmDetails> movieFavorites = filmServices.searchAllFavoriteMovies("all");
        model.addAttribute("movie",  movieFavorites);
        return "favorites";
    }

    @PostMapping("/favorites")
    public String favorites(@RequestParam String filmTitleGenre, Model model) {
        List<FilmDetails> movieFavorites = filmServices.searchAllFavoriteMovies(filmTitleGenre);
        if (movieFavorites.isEmpty()) {
            model.addAttribute("movies", null);
        } else {
            model.addAttribute("movies", movieFavorites);
        }
        return "favorites";
    }
}
