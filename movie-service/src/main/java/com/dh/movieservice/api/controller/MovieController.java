package com.dh.movieservice.api.controller;

import com.dh.movieservice.api.service.impl.MovieService;
import com.dh.movieservice.domain.models.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping ("/movies")
public class MovieController {
   @Value ("${server.port}")
   private String port;

   private static final Logger LOG = LoggerFactory.getLogger (MovieController.class);

   private final MovieService movieService;

   @Autowired
   public MovieController (MovieService movieService) {
      this.movieService = movieService;
   }

   @PostMapping
   public ResponseEntity<Movie> saveMovie (@RequestBody Movie movie) {
      return ResponseEntity.ok ().body (movieService.create (movie));
   }

   @GetMapping ("/{genre}")
   public ResponseEntity<List<Movie>> getMoviesByGenre (@PathVariable String genre, HttpServletResponse response) {
      response.addHeader ("port", port);
      List<Movie> movies = movieService.findByGenre (genre);
      LOG.info ("[MS movies-service] getMoviesByGenre {PORT: "+ port + " }");
      return movies.isEmpty ()
            ? ResponseEntity.noContent ().build ()
            : ResponseEntity.ok ().body (movies);
   }

   @GetMapping("/id/{id}")
   public ResponseEntity<Movie> getMovieById(@PathVariable Long id){
      Movie movie = movieService.findById (id);
      return ResponseEntity.ok().body (movie);
   }
   
   @GetMapping
   public ResponseEntity<List<Movie>> getAllMovies(){
      List<Movie> movies = movieService.findAll ();
      return ResponseEntity.ok ().body (movies);
   }

   /* RabbitMQ */
   @PostMapping("/save")
   ResponseEntity<String> saveMovieQueueCatalog (@RequestBody Movie movie){
      movieService.saveMovie (movie);
      return ResponseEntity.ok ("La película se envió a la queue Catalog");
   }
}
