package com.dh.seriesservice.domain.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "seasons")
public class Season {

   @Id
   @JsonSerialize (using = ToStringSerializer.class)
   private ObjectId id;

   private Integer seasonNumber;
   private List<Chapter> chapters;


   public Season () {
      //No-args constructor
   }

   public Season (Integer seasonNumber, List<Chapter> chapters) {
      this.seasonNumber = seasonNumber;
      this.chapters = chapters;
   }

   public Season (ObjectId id, Integer seasonNumber, List<Chapter> chapters) {
      this.id = id;
      this.seasonNumber = seasonNumber;
      this.chapters = chapters;
   }

   public ObjectId getId () {
      return id;
   }

   public void setId (ObjectId id) {
      this.id = id;
   }

   public Integer getSeasonNumber () {
      return seasonNumber;
   }

   public void setSeasonNumber (Integer seasonNumber) {
      this.seasonNumber = seasonNumber;
   }

   public List<Chapter> getChapters () {
      return chapters;
   }

   public void setChapters (List<Chapter> chapters) {
      this.chapters = chapters;
   }

   @Override
   public String toString () {
      return "Season{" + "id=" + id + ", seasonNumber=" + seasonNumber + ", chapters=" + chapters + '}';
   }
}
