package com.dh.seriesservice.api.service.Impl;

import com.dh.seriesservice.api.service.ISeriesService;
import com.dh.seriesservice.domain.models.Serie;
import com.dh.seriesservice.domain.repositories.ISerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SerieService implements ISeriesService {

   private ISerieRepository serieRepository;

   @Autowired
   public SerieService (ISerieRepository serieRepository) {
      this.serieRepository = serieRepository;
   }

   @Override
   @Transactional
   public Serie save (Serie serie) {
      return serieRepository.save (serie);
   }

   @Override
   @Transactional(readOnly = true)
   public List<Serie> findByGenre (String genre) {
      return serieRepository.findByGenre (genre);
   }

   @Override
   @Transactional(readOnly = true)
   public List<Serie> findAll () {
      return serieRepository.findAll (Sort.by (Sort.Order.asc ("name")));
   }
}
