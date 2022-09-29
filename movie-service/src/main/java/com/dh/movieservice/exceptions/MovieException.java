package com.dh.movieservice.exceptions;

public class MovieException extends RuntimeException{

   private static final long serialVersionUID = 1L;

   public MovieException(String msg){
      super(msg);
   }
}
