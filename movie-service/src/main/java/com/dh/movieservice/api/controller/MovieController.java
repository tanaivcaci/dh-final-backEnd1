package com.dh.movieservice.api.controller;

import com.dh.movieservice.api.service.impl.MovieService;
import com.dh.movieservice.domain.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/movies")
public class MovieController {
   private final MovieService movieService;

   @Autowired
   public MovieController (MovieService movieService) {
      this.movieService = movieService;
   }

   @PostMapping
   public ResponseEntity<Movie> saveMovie (@RequestBody Movie movie) {
      return ResponseEntity.ok ().body (movieService.create (movie));
   }

   @GetMapping ("/genre/{genre}")
   public ResponseEntity<List<Movie>> getMoviesByGenre (@PathVariable String genre) {
      List<Movie> movies = movieService.findByGenre (genre);
      if (movies.isEmpty ()) {
         return ResponseEntity.noContent ().build ();
      }
      return ResponseEntity.ok ().body (movies);
   }

   //TODO findById
   @GetMapping("/{id}")
   public ResponseEntity<Movie> getMovieById(@PathVariable Long id){
      Movie movie = movieService.findById (id);
      return ResponseEntity.ok().body (movie);
   }

   //TODO findAll

}
