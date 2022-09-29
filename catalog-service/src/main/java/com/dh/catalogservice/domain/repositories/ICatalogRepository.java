package com.dh.catalogservice.domain.repositories;

import com.dh.catalogservice.domain.models.Catalog;
import com.dh.catalogservice.domain.models.Movie;
import com.dh.catalogservice.domain.models.Serie;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICatalogRepository extends MongoRepository<Catalog, ObjectId> {

   List<Movie> findMoviesByGenre(String genre);
   List<Serie> findSeriesByGenre(String genre);
   List<Catalog> findByGenre(String genre);

}
