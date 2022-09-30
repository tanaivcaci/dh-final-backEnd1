package com.dh.catalogservice.api.feignClient;

import com.dh.catalogservice.domain.dto.MovieDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "movies-service", url = "http://localhost:8082")
@RequestMapping("/movies")
public interface MovieFeignClient {
   @GetMapping("/{genre}")
   List<MovieDTO> getMoviesByGenre(@PathVariable String genre);

}
