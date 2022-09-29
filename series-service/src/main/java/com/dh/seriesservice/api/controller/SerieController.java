package com.dh.seriesservice.api.controller;

import com.dh.seriesservice.api.service.Impl.SerieService;
import com.dh.seriesservice.domain.models.Serie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {

   private SerieService serieService;

   @Autowired
   public SerieController (SerieService serieService) {
      this.serieService = serieService;
   }

   @PostMapping
   public ResponseEntity<Serie> saveSerie(@RequestBody Serie serie){
      Serie serieSaved = serieService.save (serie);
      //TODO Enviar serie creada a RabbitMQ para que la consuma Catalog

      return ResponseEntity.ok ().body (serieSaved);
   }

   @GetMapping("/{genre}")
   public ResponseEntity<List<Serie>> getSeriesByGenre(@PathVariable String genre){
      List<Serie> series = serieService.findByGenre (genre);
      if(series.isEmpty ()){
         return ResponseEntity.noContent ().build ();
      }
      return ResponseEntity.ok ().body (series);
   }

   @GetMapping
   public ResponseEntity<List<Serie>> getAllSeries(){
      List<Serie> series = serieService.findAll ();
      return ResponseEntity.ok ().body (series);
   }



}
