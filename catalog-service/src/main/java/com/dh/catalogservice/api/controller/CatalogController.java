package com.dh.catalogservice.api.controller;

import com.dh.catalogservice.api.service.Impl.CatalogService;
import com.dh.catalogservice.domain.models.Catalog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/catalog")
public class CatalogController {
   private static final Logger LOG = LoggerFactory.getLogger (CatalogController.class);

   private CatalogService catalogService;

   @Autowired
   public CatalogController (CatalogService catalogService) {
      this.catalogService = catalogService;
   }

   @GetMapping("/{genre}")
   public ResponseEntity<Catalog> getCatalogByGenre (@PathVariable String genre){

     Catalog catalog = catalogService.getCatalogByGenre (genre);
     if(catalog == null) {
        return ResponseEntity.noContent ().build ();
     }
     return ResponseEntity.ok().body (catalog);
   }
}
