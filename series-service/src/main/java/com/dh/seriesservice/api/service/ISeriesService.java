package com.dh.seriesservice.api.service;


import com.dh.seriesservice.domain.models.Serie;

import java.util.List;

public interface ISeriesService {

   Serie save(Serie serie);
   List<Serie> findByGenre(String genre);
   List<Serie> findAll();

}
