package com.dh.catalogservice.api.controller;

import com.dh.catalogservice.api.service.Impl.CatalogService;
import com.dh.catalogservice.domain.models.Catalog;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
@RequestMapping ("/catalog")
public class CatalogController {

   @Value ("${server.port}")
   private String port;

   private static final Logger LOG = LoggerFactory.getLogger (CatalogController.class);

   private CatalogService catalogService;

   @Autowired
   public CatalogController (CatalogService catalogService) {
      this.catalogService = catalogService;
   }

   /*    @CircuitBreaker
    * Indica que se realizará un seguimiento en éste endpoint
    * name: es el nombre configurado en /config-data/catalog-service-dev.yml
    * de donde el MS config server toma las propiedades
    * fallbackMethod: indica el método que se ejecutará en caso de que falle el método actual.
    * */

   @CircuitBreaker (name = "catalogCB", fallbackMethod = "getCatalogByGenreFallBackMethod")
   @GetMapping ("/{genre}")
   public ResponseEntity<Catalog> getCatalogByGenre (@PathVariable String genre, HttpServletResponse response) {
      //TODO OJO DEBERÍA TRABAJAR CON RESPONSE ENTITYS OBTENIDAS A TRAVÉS DE FEIGN Y LOGGUEAR - mirar parcial

      Catalog catalog = catalogService.getCatalogByGenre (genre);

      response.addHeader ("port", String.valueOf (port));

      ResponseEntity<Catalog> catalogResponse;

      if (catalog == null) {
         catalogResponse = ResponseEntity.noContent ().build ();
         LOG.info ("Puerto CatalogResponse: " + catalogResponse.getHeaders ().get ("port"));
         return catalogResponse;
      }
      catalogResponse = ResponseEntity.ok ().body (catalog);
      LOG.info ("Puerto: " + catalogResponse.getHeaders ().get ("port") + port);
      return catalogResponse;
   }



   /*----------------- Método de Fallback ---------------*/

   private ResponseEntity<Catalog> getCatalogByGenreFallBackMethod (CallNotPermittedException exception) {
      LOG.info ("Se activo el circuit breaker para obtener un catálogo por género");
      ResponseEntity<Catalog> response = ResponseEntity.ok ().build ();
      return response;
   }
}
