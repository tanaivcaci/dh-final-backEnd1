package com.dh.catalogservice.api.service.Impl;

import com.dh.catalogservice.api.feignClient.MovieFeignClient;
import com.dh.catalogservice.api.feignClient.SerieFeignClient;
import com.dh.catalogservice.api.service.ICatalogService;
import com.dh.catalogservice.domain.dto.MovieDTO;
import com.dh.catalogservice.domain.dto.SerieDTO;
import com.dh.catalogservice.domain.models.Catalog;
import com.dh.catalogservice.domain.repositories.ICatalogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


   /*----------------- OBTENGO EL LISTADO DE MOVIES Y SERIES A TRAVÉS DE FEIGN---------------*/

   //TODO AGREGAR CIRCUIT BREAKER, FALLBACKS, RETRYS

   @Override
   public List<SerieDTO> getSeriesByGenre (String genre) {
      return serieFeignClient.getSeriesByGenre (genre);
   }

   //TODO AGREGAR CIRCUIT BREAKER, FALLBACKS, RETRYS
   @Override
   public List<MovieDTO> getMoviesByGenre (String genre) {
      return movieFeignClient.getMoviesByGenre (genre);
   }


   /*----------------- OBTENGO Y ACTUALIZO CATÁLOGO ---------------*/

   @Override
   public Catalog getCatalogByGenre (String genre) {
      updateCatalogByGenre (genre);
      return catalogRepository.findByGenre (genre);
   }

   @Override
   public void updateCatalogByGenre (String genre) {
      List<MovieDTO> moviesByGenre = getMoviesByGenre (genre);
      List<SerieDTO> seriesByGenre = getSeriesByGenre (genre);
      Catalog catalog = catalogRepository.findByGenre (genre);

      if(catalog != null){
         catalog.setMovies (moviesByGenre);
         catalog.setSerieDTOS (seriesByGenre);
         catalogRepository.save (catalog);
      } else {
         catalogRepository.save (Catalog.builder()
               .genre(genre)
               .movieDTOS (moviesByGenre)
               .serieDTOS (seriesByGenre)
               .build());
      }
   }


}



