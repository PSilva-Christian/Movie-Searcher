package org.silvachristian.searchfilms.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "movies")
public class FilmDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JsonProperty("Title")
    String filmTitle;

    @JsonProperty("Metascore")
    String filmRating;

    @JsonProperty("Genre")
    String filmGenre;

    @JsonProperty("Year")
    String filmYear;

    @JsonProperty("Poster")
    String posterURL;

    @JsonProperty("Country")
    String filmCountry;

    @JsonProperty("Type")
    String filmType;

    @JsonProperty("Plot")
    String filmPlot;

}

