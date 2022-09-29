package com.dh.catalogservice.domain.models;

import java.util.List;

public class Serie {
   private String id;
   private String name;
   private String genre;

   private List<Season> seasons;

   public Serie () {
      //No-args constructor
   }

   public Serie (String name, String genre, List<Season> seasons) {
      this.name = name;
      this.genre = genre;
      this.seasons = seasons;
   }

   public Serie (String id, String name, String genre, List<Season> seasons) {
      this.id = id;
      this.name = name;
      this.genre = genre;
      this.seasons = seasons;
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

   public List<Season> getSeasons () {
      return seasons;
   }

   public void setSeasons (List<Season> seasons) {
      this.seasons = seasons;
   }

   @Override
   public String toString () {
      return "Serie{" + "id=" + id + ", name='" + name + '\'' + ", genre='" + genre + '\'' + ", seasons=" + seasons + '}';
   }
}
