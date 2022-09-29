package com.dh.catalogservice.domain.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "catalogs")
public class Catalog {

   @Id
   @JsonSerialize(using = ToStringSerializer.class)
   private ObjectId id;

   private String genre;

   private List<Movie> movies;
   private List<Serie> series;

   public Catalog () {
      //No-args constructor
   }

   public Catalog (ObjectId id, String genre, List<Movie> movies, List<Serie> series) {
      this.id = id;
      this.genre = genre;
      this.movies = movies;
      this.series = series;
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

   public List<Movie> getMovies () {
      return movies;
   }

   public void setMovies (List<Movie> movies) {
      this.movies = movies;
   }

   public List<Serie> getSeries () {
      return series;
   }

   public void setSeries (List<Serie> series) {
      this.series = series;
   }

   @Override
   public String toString () {
      return "Catalog{" + "id=" + id + ", genre='" + genre + '\'' + ", movies=" + movies + ", series=" + series + '}';
   }
}
