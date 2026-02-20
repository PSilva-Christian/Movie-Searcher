package org.silvachristian.searchfilms.repository;

import org.silvachristian.searchfilms.entity.FilmDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<FilmDetails, Long> {

    FilmDetails findFilmDetailsByFilmTitle(String movieTitle);

    boolean existsByFilmTitle(String movieTitle);

    List<FilmDetails> findAllByFilmGenreContaining(String filmGenre);


}
