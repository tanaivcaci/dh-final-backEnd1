package com.dh.catalogservice.api.feignClient;

import com.dh.catalogservice.domain.dto.SerieDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "series-service", url = "http://localhost:8083")
public interface SerieFeignClient {
   @GetMapping("/series/{genre}")
   List<SerieDTO> getSeriesByGenre(@PathVariable String genre);

}
