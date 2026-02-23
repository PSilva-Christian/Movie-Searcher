package org.silvachristian.searchfilms.repository;

import org.silvachristian.searchfilms.entity.FavoritesInfo;
import org.silvachristian.searchfilms.entity.MovieInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieInfo, Long> {

    MovieInfo findFilmDetailsByFilmTitle(String movieTitle);

    boolean existsByFilmTitle(String movieTitle);

    @Query("SELECT m.filmTitle AS filmTitle, m.posterURL AS posterURL, m.filmGenre AS filmGenre FROM movies m WHERE m.filmGenre LIKE %:genre% ")
    List<FavoritesInfo> findByGenre(String genre);

    @Query("SELECT m.filmTitle AS filmTitle, m.posterURL AS posterURL, m.filmGenre AS filmGenre FROM movies m")
    List<FavoritesInfo> findAllFavorites();


}
