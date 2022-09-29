package com.dh.seriesservice.domain.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "chapters")
public class Chapter {

   @Id
   @JsonSerialize (using = ToStringSerializer.class)
   private ObjectId id;

   private String name;
   private Integer number;
   private String urlStream;

   public Chapter () {
      //No-args constructor
   }

   public Chapter (String name, Integer number, String urlStream) {
      this.name = name;
      this.number = number;
      this.urlStream = urlStream;
   }

   public Chapter (ObjectId id, String name, Integer number, String urlStream) {
      this.id = id;
      this.name = name;
      this.number = number;
      this.urlStream = urlStream;
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

   public Integer getNumber () {
      return number;
   }

   public void setNumber (Integer number) {
      this.number = number;
   }

   public String getUrlStream () {
      return urlStream;
   }

   public void setUrlStream (String urlStream) {
      this.urlStream = urlStream;
   }

   @Override
   public String toString () {
      return "Chapter{" + "id=" + id + ", name='" + name + '\'' + ", number=" + number + ", urlStream='" + urlStream + '\'' + '}';
   }
}
