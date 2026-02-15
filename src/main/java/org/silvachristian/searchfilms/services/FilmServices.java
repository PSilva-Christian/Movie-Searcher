package org.silvachristian.searchfilms.services;

import org.springframework.beans.factory.annotation.Value;
import org.silvachristian.searchfilms.entity.FilmDetails;
import org.silvachristian.searchfilms.repository.FilmRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class FilmServices {

    private final RestClient restClient;
    private final FilmRepository movieRepository;
    private final String apiKey;

    FilmServices(RestClient.Builder builder, FilmRepository movieRepository,
                 @Value("${omdb-api-key}") String apiKeyProperties) {
        this.restClient = builder.baseUrl("http://www.omdbapi.com/").build();
        this.movieRepository = movieRepository;
        this.apiKey = apiKeyProperties;
    }

    public boolean checkIfAlreadySearched(String movieTitle) {
        return movieRepository.existsByFilmTitle(movieTitle);
    }

    public FilmDetails findByTitle(String movieTitle) {
        movieTitle = stringToCapital(movieTitle);
        if (checkIfAlreadySearched(movieTitle)) {
            return movieRepository.findFilmDetailsByFilmTitle(movieTitle);
        }
        else {
            String finalMovieTitle = movieTitle;
            return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("t", finalMovieTitle)
                        .queryParam("apikey", apiKey)
                        .build())
                .retrieve()
                .body(FilmDetails.class);
        }
    }

    public String stringToCapital(String movieTitle){

        String [] movieWords = movieTitle.split(" ");
        StringBuilder finalMovieTitle = new StringBuilder();
        for(String movieWord : movieWords){
            movieWord = movieWord.substring(0, 1).toUpperCase() + movieWord.substring(1).toLowerCase();
            finalMovieTitle.append(" ").append(movieWord);
        }
        return finalMovieTitle.toString().trim();
    }
}


