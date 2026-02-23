package org.silvachristian.searchfilms.services;

import org.springframework.beans.factory.annotation.Value;
import org.silvachristian.searchfilms.entity.MovieInfo;
import org.silvachristian.searchfilms.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class MovieServices {

    private final RestClient restClient;
    private final MovieRepository movieRepository;
    private final String apiKey;

    MovieServices(RestClient.Builder builder, MovieRepository movieRepository,
                  @Value("${omdb-api-key}") String apiKeyProperties) {
        this.restClient = builder.baseUrl("http://www.omdbapi.com/").build();
        this.movieRepository = movieRepository;
        this.apiKey = apiKeyProperties;
    }

    public boolean checkIfAlreadySearched(String movieTitle) {
        return movieRepository.existsByFilmTitle(movieTitle);
    }

    public MovieInfo findByTitle(String movieTitle) {
        movieTitle = stringToCapital(movieTitle);
        if (checkIfAlreadySearched(movieTitle)) {
            return movieRepository.findFilmDetailsByFilmTitle(movieTitle);
        } else {
            String finalMovieTitle = movieTitle;
            MovieInfo newMovie = restClient.get().uri(uriBuilder -> uriBuilder
                    .queryParam("t", finalMovieTitle)
                    .queryParam("apikey", apiKey)
                    .build()).retrieve().body(MovieInfo.class);

            assert newMovie != null;
            movieRepository.save(newMovie);
            return newMovie;

        }
    }

    public String stringToCapital(String movieTitle) {

        String[] movieWords = movieTitle.split(" ");
        StringBuilder finalMovieTitle = new StringBuilder();
        for (String movieWord : movieWords) {
            movieWord = movieWord.substring(0, 1).toUpperCase() + movieWord.substring(1).toLowerCase();
            finalMovieTitle.append(" ").append(movieWord);
        }
        return finalMovieTitle.toString().trim();
    }
}



