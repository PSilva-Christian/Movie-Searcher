package org.silvachristian.searchfilms.services;

import org.silvachristian.searchfilms.entity.FilmDetails;
import org.silvachristian.searchfilms.repository.FilmRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class FilmServices {

    private final RestClient restClient;
    private final FilmRepository movieRepository;


    private final String apiKey = "{omdb.api.key}";

    FilmServices(RestClient.Builder builder, FilmRepository movieRepository) {
        this.restClient = builder.baseUrl("http://www.omdbapi.com/").build();
        this.movieRepository = movieRepository;
    }

    public Long checkIfAlreadySearched(String movieTitle) {
        return movieRepository.findFilmDetailsByFilmTitle (movieTitle);

    }

    public FilmDetails findByTitle(String movieTitle) {
        Long idCheck = checkIfAlreadySearched(movieTitle);
        if (idCheck != null){
            return movieRepository.findById(idCheck).orElse(null);
        }
        else {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("t", movieTitle) // Usi
                        .queryParam("apikey", apiKey)
                        .build())
                .retrieve()
                .body(FilmDetails.class);
        }
    }

}


