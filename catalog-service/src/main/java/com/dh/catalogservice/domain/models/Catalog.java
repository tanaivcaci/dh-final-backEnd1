package com.dh.catalogservice.domain.models;

import com.dh.catalogservice.domain.dto.MovieDTO;
import com.dh.catalogservice.domain.dto.SerieDTO;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Builder;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Builder
@Document(collection = "catalogs")
public class Catalog {

   @Id
   @JsonSerialize(using = ToStringSerializer.class)
   private ObjectId id;
   private String genre;
   private List<MovieDTO> movieDTOS;
   private List<SerieDTO> serieDTOS;

   public Catalog () {
      super();
      //No-args constructor
   }

   public Catalog (ObjectId id, String genre, List<MovieDTO> movieDTOS, List<SerieDTO> seriesDTOS) {
      super();
      this.id = id;
      this.genre = genre;
      this.movieDTOS = movieDTOS;
      this.serieDTOS = seriesDTOS;
   }

   public Catalog (String genre, List<MovieDTO> movieDTOS, List<SerieDTO> serieDTOS) {
      super();
      this.genre = genre;
      this.movieDTOS = movieDTOS;
      this.serieDTOS = serieDTOS;
   }

   public ObjectId getId () {
      return id;
   }

   public void setId (ObjectId id) {
      this.id = id;
   }

   public String getGenre () {
      return genre;
   }

   public void setGenre (String genre) {
      this.genre = genre;
   }

   public List<MovieDTO> getMovies () {
      return movieDTOS;
   }

   public void setMovies (List<MovieDTO> movieDTOS) {
      this.movieDTOS = movieDTOS;
   }

   public List<SerieDTO> getSerieDTOS () {
      return serieDTOS;
   }

   public void setSerieDTOS (List<SerieDTO> serieDTOS) {
      this.serieDTOS = serieDTOS;
   }

   @Override
   public String toString () {
      return "Catalog{" + "id=" + id + ", genre='" + genre + '\'' + ", movieDTOS=" + movieDTOS + ", series=" + serieDTOS + '}';
   }
}
