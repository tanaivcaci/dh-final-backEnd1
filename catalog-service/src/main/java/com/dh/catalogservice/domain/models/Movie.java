package com.dh.catalogservice.domain.models;


public class Movie {

   private Long id;
   private String name;
   private String genre;
   private String urlStream;

   public Movie () {
      //No-args constructor
   }

   public Movie (Long id, String name, String genre, String urlStream) {
      this.id = id;
      this.name = name;
      this.genre = genre;
      this.urlStream = urlStream;
   }

   public Long getId () {
      return id;
   }

   public void setId (Long id) {
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

   public String getUrlStream () {
      return urlStream;
   }

   public void setUrlStream (String urlStream) {
      this.urlStream = urlStream;
   }

   @Override
   public String toString () {
      return "Movie{" + "id=" + id + ", name='" + name + '\'' + ", genre='" + genre + '\'' + ", urlStream='" + urlStream + '\'' + '}';
   }
}
