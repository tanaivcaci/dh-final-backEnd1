package com.dh.movieservice;

import com.dh.movieservice.api.controller.MovieController;
import com.dh.movieservice.api.service.impl.MovieService;
import com.dh.movieservice.data.Data;
import com.dh.movieservice.domain.models.Movie;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

//import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.*;

@WebMvcTest(MovieController.class)
public class MovieControllerTest {

   @Autowired
   private MockMvc mockMvc;

   @MockBean
   private MovieService movieService;

   ObjectMapper objectMapper;

   @BeforeEach
   void setUp(){
      objectMapper = new ObjectMapper ();
   }

   @Test
   void testGetMovie() throws Exception {
      when (movieService.findById (1L))
            .thenReturn (Data.createMovie1()
                  .orElseThrow());

      mockMvc.perform (get ("/movies/1")
            .contentType (MediaType.APPLICATION_JSON))
            .andExpect (status ().isOk ())
/*            .andExpect (content().contentType (MediaType.APPLICATION_JSON))*/
            .andExpect (jsonPath ("$.name").value ("Peli1"))
            .andExpect (jsonPath ("$.genre").value ("comedia"))
            .andExpect (jsonPath ("$.urlStream").value ("https://youtu.be/7o7HUnim7oM"));

      verify (movieService).findById (1L);
   }


   @Test
   void testGetAllMovies() throws Exception {
      List<Movie> movies = Arrays.asList (
            Data.createMovie1 ().orElseThrow (),
            Data.createMovie2 ().orElseThrow ());

            when(movieService.findAll ())
                  .thenReturn (movies);


      mockMvc.perform (get("/movies")
                  .contentType (MediaType.APPLICATION_JSON))
  /*                .andExpect ((ResultMatcher) content ().contentType (MediaType.APPLICATION_JSON))*/
                  .andExpect (jsonPath("$[0].name").value ("Peli1"))
                  .andExpect (jsonPath("$[1].name").value ("Peli2"))
                  .andExpect (jsonPath("$[0].genre").value ("comedia"))
                  .andExpect (jsonPath("$[1].genre").value ("terror"))
                  .andExpect (jsonPath ("$", hasSize (2)));

      verify (movieService).findAll ();
   }

   @Test
   void testGetMoviesByGenre() throws Exception {
      List<Movie> movies = Arrays.asList (
            Data.createMovie1 ().orElseThrow (),
            Data.createMovie3 ().orElseThrow ());

      when(movieService.findByGenre ("comedia"))
            .thenReturn (movies);


      mockMvc.perform (get("/movies/genre/comedia")
                  .contentType (MediaType.APPLICATION_JSON))
            .andExpect (jsonPath("$[0].name").value ("Peli1"))
            .andExpect (jsonPath("$[1].name").value ("Peli3"))
            .andExpect (jsonPath("$[0].genre").value ("comedia"))
            .andExpect (jsonPath("$[1].genre").value ("comedia"))
            .andExpect (jsonPath ("$", hasSize (2)));

      verify (movieService).findByGenre ("comedia");
   }


   /*@Test
   void testCreateMovie() throws Exception {
      Movie movie = new Movie(5L, "Peli4", "comedia", "https://youtu.be/7o7HUnim7oM");
      when (movieService.create (any ())).then (invocation -> {
         Movie m = invocation.getArgument (0);
         m.setName ("Peli5");
         return m;
      });

      mockMvc.perform (post("/movies").contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString (movie)))
*//*            .andExpect (content().contentType (MediaType.APPLICATION_JSON))*//*
            .andExpect (jsonPath ("$.id", is (5)))
            .andExpect (jsonPath ("$.name", is("Peli5")))
            .andExpect (jsonPath ("$.genre", is("comedia")))
            .andExpect (jsonPath ("$.urlStream", is("https://youtu.be/7o7HUnim7oM")));

      verify (movieService).create (movie);

   }*/
}
