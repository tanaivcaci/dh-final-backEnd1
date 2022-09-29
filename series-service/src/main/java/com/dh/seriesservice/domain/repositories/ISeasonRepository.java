package com.dh.seriesservice.domain.repositories;

import com.dh.seriesservice.domain.models.Season;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISeasonRepository extends MongoRepository<Season, ObjectId> {

}
