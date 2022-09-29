package com.dh.movieservice.data;

import com.dh.movieservice.domain.models.Movie;

import java.util.Optional;

public class Data {


   public static Optional<Movie> createMovie1 () {
      return Optional.of (new Movie (1L, "Peli1", "comedia", "https://youtu.be/7o7HUnim7oM"));
   }

   public static Optional<Movie> createMovie2 () {
      return Optional.of (new Movie (2L, "Peli2", "terror", "https://youtu.be/7o7HUnim7oM"));
   }

   public static Optional<Movie> createMovie3 () {
      return Optional.of (new Movie (3L, "Peli3", "comedia", "https://youtu.be/7o7HUnim7oM"));
   }



}
