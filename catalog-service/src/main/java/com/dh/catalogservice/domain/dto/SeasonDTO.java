package com.dh.catalogservice.domain.dto;

import java.util.List;

public class SeasonDTO {

   private String id;
   private Integer seasonNumber;
   private List<ChapterDTO> chapterDTOS;


   public SeasonDTO () {
      //No-args constructor
   }

   public SeasonDTO (Integer seasonNumber, List<ChapterDTO> chapterDTOS) {
      this.seasonNumber = seasonNumber;
      this.chapterDTOS = chapterDTOS;
   }

   public SeasonDTO (String id, Integer seasonNumber, List<ChapterDTO> chapterDTOS) {
      this.id = id;
      this.seasonNumber = seasonNumber;
      this.chapterDTOS = chapterDTOS;
   }

   public String getId () {
      return id;
   }

   public void setId (String id) {
      this.id = id;
   }

   public Integer getSeasonNumber () {
      return seasonNumber;
   }

   public void setSeasonNumber (Integer seasonNumber) {
      this.seasonNumber = seasonNumber;
   }

   public List<ChapterDTO> getChapters () {
      return chapterDTOS;
   }

   public void setChapters (List<ChapterDTO> chapterDTOS) {
      this.chapterDTOS = chapterDTOS;
   }

   @Override
   public String toString () {
      return "SeasonDTO{" + "id=" + id + ", seasonNumber=" + seasonNumber + ", chapterDTOS=" + chapterDTOS + '}';
   }
}
