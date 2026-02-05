package org.silvachristian.searchfilms.services;

import org.silvachristian.searchfilms.entity.FilmDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class FilmServices {

    private final RestClient restClient;

    private final String apiKey = "apikey";

    FilmServices(RestClient.Builder builder) {
        this.restClient = builder.baseUrl("http://www.omdbapi.com/").build();
    }

    public FilmDetails findByTitle(String movieTitle) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("t", movieTitle) // Usi
                        .queryParam("apikey", apiKey)
                        .build())
                .retrieve()
                .body(FilmDetails.class);
    }

}


