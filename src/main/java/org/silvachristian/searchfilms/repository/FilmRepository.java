package org.silvachristian.searchfilms.repository;

import org.silvachristian.searchfilms.entity.FilmDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends CrudRepository<FilmDetails, Long> {
}
