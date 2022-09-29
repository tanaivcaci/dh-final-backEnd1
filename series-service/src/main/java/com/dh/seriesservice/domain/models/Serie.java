package com.dh.seriesservice.domain.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "series")
public class Serie {

   @Id
   @JsonSerialize(using = ToStringSerializer.class)
   private ObjectId id;
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

   public Serie (ObjectId id, String name, String genre, List<Season> seasons) {
      this.id = id;
      this.name = name;
      this.genre = genre;
      this.seasons = seasons;
   }

   public ObjectId getId () {
      return id;
   }

   public void setId (ObjectId id) {
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
