package com.dh.catalogservice.domain.dto;

import java.util.List;

public class SerieDTO {
   private String id;
   private String name;
   private String genre;
   private List<SeasonDTO> seasonDTOS;

   public SerieDTO () {
      //No-args constructor
   }

   public SerieDTO (String name, String genre, List<SeasonDTO> seasonDTOS) {
      this.name = name;
      this.genre = genre;
      this.seasonDTOS = seasonDTOS;
   }

   public SerieDTO (String id, String name, String genre, List<SeasonDTO> seasonDTOS) {
      this.id = id;
      this.name = name;
      this.genre = genre;
      this.seasonDTOS = seasonDTOS;
   }

   public String getId () {
      return id;
   }

   public void setId (String id) {
      this.id = id;
   }

   public String getName () {
      return name;
   }

   public void setName (String name) {
      this.name = name;
   }

   public String getGenre () {
      return genre;
   }

   public void setGenre (String genre) {
      this.genre = genre;
   }

   public List<SeasonDTO> getSeasons () {
      return seasonDTOS;
   }

   public void setSeasons (List<SeasonDTO> seasonDTOS) {
      this.seasonDTOS = seasonDTOS;
   }

   @Override
   public String toString () {
      return "SerieDTO{" + "id=" + id + ", name='" + name + '\'' + ", genre='" + genre + '\'' + ", seasonDTOS=" + seasonDTOS + '}';
   }
}
