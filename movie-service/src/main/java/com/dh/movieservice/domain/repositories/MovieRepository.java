package com.dh.movieservice.domain.repositories;

import com.dh.movieservice.domain.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
   List<Movie> findByGenre(String genre);

}
