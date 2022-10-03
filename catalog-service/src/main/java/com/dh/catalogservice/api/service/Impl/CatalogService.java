package com.dh.catalogservice.api.service.Impl;

import com.dh.catalogservice.api.feignClient.MovieFeignClient;
import com.dh.catalogservice.api.feignClient.SerieFeignClient;
import com.dh.catalogservice.api.service.ICatalogService;
import com.dh.catalogservice.domain.dto.MovieDTO;
import com.dh.catalogservice.domain.dto.SerieDTO;
import com.dh.catalogservice.domain.models.Catalog;
import com.dh.catalogservice.domain.repositories.ICatalogRepository;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogService implements ICatalogService, MovieFeignClient, SerieFeignClient {

   private static final Logger LOG = LoggerFactory.getLogger (CatalogService.class);

   private ICatalogRepository catalogRepository;
   private MovieFeignClient movieFeignClient;
   private SerieFeignClient serieFeignClient;


   @Autowired
   public CatalogService (ICatalogRepository catalogRepository
                           , MovieFeignClient movieFeignClient
                           , SerieFeignClient serieFeignClient) {
      this.catalogRepository = catalogRepository;
      this.movieFeignClient = movieFeignClient;
      this.serieFeignClient = serieFeignClient;
   }




   /*----------------- MOVIES · CIRCUIT BREAKER · FALLBACK METHOD ----------------------------*/


   /*--              OBTENGO EL LISTADO DE MOVIES Y SERIES A TRAVÉS DE FEIGN  ---------------*/
   public ResponseEntity<List<MovieDTO>> getMoviesByGenre (String genre) {
      LOG.info ("Se va a incluir el llamado a movie-service ... ");
      return movieFeignClient.getMoviesByGenre (genre);
   }

   @Override
   @CircuitBreaker (name = "moviesCB", fallbackMethod = "moviesFallbackMethod")
   public ResponseEntity<List<MovieDTO>> getMoviesByGenreWithThrowError (String genre, Boolean throwError) {
      LOG.info ("Llamo a movie-service WithThrowError ... ");
      return movieFeignClient.getMoviesByGenreWithThrowError (genre, throwError);
   }

   private ResponseEntity<List<MovieDTO>> moviesFallbackMethod(CallNotPermittedException exception){
      LOG.info ("--- Se activó el circuitbreaker --- MOVIESCB ");
      return new ResponseEntity<> (new ArrayList<> (), HttpStatus.OK);
   }


   /*   public Catalog getMoviesByGenre (String genre) {
      ResponseEntity<List<MovieDTO>> moviesByGenre = movieFeignClient.getMoviesByGenre (genre);
      LOG.info ("Puerto: " + moviesByGenre.getHeaders ().get ("port"));

      if (moviesByGenre.getStatusCode ().is2xxSuccessful ()){
         return new Catalog (genre, moviesByGenre.getBody ());
      }
      return null;
   }*/

   /*----------------- SERIES · CIRCUIT BREAKER · FALLBACK METHOD ----------------------------*/
   @Override
   public ResponseEntity<List<SerieDTO>> getSeriesByGenre (String genre) {
      LOG.info ("Se va a incluir el llamado a series-service ... ");
      return serieFeignClient.getSeriesByGenre (genre);
   }

   @Override
   @CircuitBreaker (name = "seriesCB", fallbackMethod = "seriesFallbackMethod")
   public ResponseEntity<List<SerieDTO>> getSeriesByGenreWithThrowError (String genre, boolean throwError) {
      LOG.info ("Llamo a serie-service WithThrowError ");
      return serieFeignClient.getSeriesByGenreWithThrowError (genre, throwError);
   }

   private ResponseEntity<List<SerieDTO>> seriesFallbackMethod (CallNotPermittedException exception) {
      LOG.info ("Se activó el circuitbreaker --- SERIESCB ");
      return new ResponseEntity<> (new ArrayList<> (), HttpStatus.OK);
   }


   /*----------------- OBTENGO Y ACTUALIZO CATÁLOGO ---------------*/

   @Override
   public Catalog getCatalogByGenre (String genre) {
      updateCatalogByGenre (genre);
      return catalogRepository.findByGenre (genre);
   }

   @Override
   public void updateCatalogByGenre (String genre) {
      ResponseEntity<List<MovieDTO>> moviesByGenre = getMoviesByGenre (genre);
      LOG.info ("Puerto MOVIES: " + moviesByGenre.getHeaders ().get ("port"));

      ResponseEntity<List<SerieDTO>> seriesByGenre = getSeriesByGenre (genre);
      Catalog catalog = catalogRepository.findByGenre (genre);

      if(catalog != null){
         catalog.setMovies (moviesByGenre.getBody ());
         catalog.setSerieDTOS (seriesByGenre.getBody ());
         catalogRepository.save (catalog);
      } else {
         catalogRepository.save (Catalog.builder()
               .genre(genre)
               .movieDTOS (moviesByGenre.getBody ())
               .serieDTOS (seriesByGenre.getBody ())
               .build());
      }
   }
}



