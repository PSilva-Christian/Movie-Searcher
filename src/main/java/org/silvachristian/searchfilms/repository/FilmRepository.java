package org.silvachristian.searchfilms.repository;

import org.silvachristian.searchfilms.entity.FilmDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<FilmDetails, Long> {

    FilmDetails findFilmDetailsByFilmTitle(String movieTitle);

    boolean existsByFilmTitle(String movieTitle);

}
