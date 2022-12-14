package com.dh.movieservice.api.service.impl;

import com.dh.movieservice.api.service.IMovieService;
import com.dh.movieservice.domain.models.Movie;
import com.dh.movieservice.domain.repositories.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovieService implements IMovieService {

   /* rabbitMQ */
   /* Nombre de la cola que configure en bootstrap */
   @Value ("${queue.catalog.name}")
   private String movieQueueCatalog;
   private RabbitTemplate rabbitTemplate;

   private static final Logger LOG = LoggerFactory.getLogger (MovieService.class);

   private final MovieRepository movieRepository;

   @Autowired
   public MovieService(MovieRepository movieRepository, RabbitTemplate rabbitTemplate){
      this.movieRepository = movieRepository;
      this.rabbitTemplate = rabbitTemplate;
   }

   @Override
   public Movie create (Movie movie) {
      return movieRepository.save (movie);
   }


   @Override
   @Transactional(readOnly = true)
   public Movie findById (Long id) {
      return movieRepository.findById (id).orElse (null);
   }

   @Override
   @Transactional(readOnly = true)
   public List<Movie> findByGenre (String genre) {
      //TODO ver C20 repo profe - redisUtils
      List<Movie> movies = movieRepository.findByGenre (genre);
      return movies;
   }

   @Override
   @Transactional(readOnly = true)
   public List<Movie> findAll () {
      /*https://tedblob.com/spring-data-jpa-findall-order-by/*/
      List<Movie> movies = movieRepository.findAll(Sort.by (Sort.Order.asc ("name")));
      return movies;
   }

   /* RabbbitMQ */
   public void saveMovie(Movie movie){
      rabbitTemplate.convertAndSend (movieQueueCatalog, movie);
   }
   /* Fin RabbitMQ */
}
