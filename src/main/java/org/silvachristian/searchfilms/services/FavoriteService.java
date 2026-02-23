package org.silvachristian.searchfilms.services;

import org.silvachristian.searchfilms.entity.FavoritesInfo;
import org.silvachristian.searchfilms.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class FavoriteService {

    MovieServices movieServices;
    private final MovieRepository movieRepository;

    FavoriteService (MovieServices movieServices, MovieRepository movieRepository){
        this.movieServices = movieServices;
        this.movieRepository = movieRepository;
    }

    public List<FavoritesInfo> showFavorites(@RequestParam String movieGenre){
        if(movieGenre.isEmpty() || movieGenre.equals("all")){
            return movieRepository.findAllFavorites();
        } else {
            return movieRepository.findByGenre(movieServices.stringToCapital(movieGenre));
        }
    }
}
