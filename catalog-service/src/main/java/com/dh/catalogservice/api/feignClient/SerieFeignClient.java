package com.dh.catalogservice.api.feignClient;

import com.dh.catalogservice.config.CustomLoadBalancerConfiguration;
import com.dh.catalogservice.domain.dto.SerieDTO;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "series-service")
@LoadBalancerClient ( name = "series-services", configuration = CustomLoadBalancerConfiguration.class)
public interface SerieFeignClient {

   //TODO DEBERÍA DEVOLVER RESPONSE ENTITYS DE LOS DTOS - VER PARCIAL
/*   @GetMapping("/series/{genre}")
   List<SerieDTO> getSeriesByGenre(@PathVariable String genre);*/

   @GetMapping("/series/{genre}")
   ResponseEntity<List<SerieDTO>> getSeriesByGenre(@PathVariable(value = "genre") String genre);

   @GetMapping("/series/withErrors/{genre}")
   ResponseEntity<List<SerieDTO>> getSeriesByGenreWithThrowError (
         @PathVariable (value = "genre") String genre,
         @RequestParam("throwError") boolean throwError);

}
