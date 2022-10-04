package com.dh.seriesservice.api.service.Impl;

import com.dh.seriesservice.api.service.ISeriesService;
import com.dh.seriesservice.domain.models.Serie;
import com.dh.seriesservice.domain.repositories.ISerieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SerieService implements ISeriesService {

   @Value ("${queue.catalog.name}")
   private String serieQueueCatalog;
   private RabbitTemplate rabbitTemplate;

   private static final Logger LOG = LoggerFactory.getLogger (SerieService.class);

   private ISerieRepository serieRepository;

   @Autowired
   public SerieService (ISerieRepository serieRepository, RabbitTemplate rabbitTemplate) {
      this.serieRepository = serieRepository;
      this.rabbitTemplate = rabbitTemplate;
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



   /* RabbbitMQ */
   public void saveSerie(Serie serie){
      rabbitTemplate.convertAndSend (serieQueueCatalog, serie);
   }
   /* Fin RabbitMQ */
}
