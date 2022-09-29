package com.dh.seriesservice.domain.repositories;

import com.dh.seriesservice.domain.models.Serie;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISerieRepository extends MongoRepository<Serie, ObjectId> {

   List<Serie> findByGenre(String genre);

}
