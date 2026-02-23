package org.silvachristian.searchfilms.controllers;

import org.silvachristian.searchfilms.entity.FavoritesInfo;
import org.silvachristian.searchfilms.entity.MovieInfo;
import org.silvachristian.searchfilms.repository.MovieRepository;
import org.silvachristian.searchfilms.services.FavoriteService;
import org.silvachristian.searchfilms.services.MovieServices;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MovieController {

    MovieServices movieServices;
    MovieRepository movieRepository;
    FavoriteService favoriteService;

    MovieController(MovieServices filmServices, MovieRepository movieRepository, FavoriteService favoriteService) {
        this.movieServices = filmServices;
        this.movieRepository = movieRepository;
        this.favoriteService = favoriteService;
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("movie", new MovieInfo());
        return "/movies/home";
    }

    @PostMapping("/home")
    public String postHome(@AuthenticationPrincipal UserDetails userDetails,
                           @RequestParam String movieTitle, Model model) {
        MovieInfo movie = movieServices.findByTitle(movieTitle);
        model.addAttribute("movie", movie);

        return "/movies/home";
    }

    @GetMapping("/favorites")
    public String favorites(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        List<FavoritesInfo> movieFavorites = favoriteService.showFavorites("all");
        model.addAttribute("movie",  movieFavorites);
        model.addAttribute("username", userDetails.getUsername());
        return "/movies/favorites";
    }

    @PostMapping("/favorites")
    public String favorites(@RequestParam String movieGenre, Model model) {
        List<FavoritesInfo> movieFavorites = favoriteService.showFavorites(movieGenre);
        if (movieFavorites.isEmpty()) {
            model.addAttribute("movies", null);
        } else {
            model.addAttribute("movies", movieFavorites);
        }
        return "/movies/favorites";
    }
}
