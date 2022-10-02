package com.dh.catalogservice.api.feignClient;

import com.dh.catalogservice.config.CustomLoadBalancerConfiguration;
import com.dh.catalogservice.domain.dto.MovieDTO;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "movies-service")
@LoadBalancerClient( name = "movies-services", configuration = CustomLoadBalancerConfiguration.class)
public interface MovieFeignClient {

/*   @GetMapping("/movies/{genre}")
   List<MovieDTO> getMoviesByGenre(@PathVariable String genre);*/

   @GetMapping("/movies/{genre}")
   ResponseEntity<List<MovieDTO>> getMoviesByGenre(@PathVariable("genre") String genre);
}
