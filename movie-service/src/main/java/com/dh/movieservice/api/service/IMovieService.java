package com.dh.movieservice.api.service;

import com.dh.movieservice.domain.models.Movie;

import java.util.List;

public interface IMovieService {

   Movie create(Movie movie);

   List<Movie> findByGenre(String genre);


      List<Movie> findAll();
      Movie findById(Long id);
}
