package com.dh.seriesservice.domain.repositories;

import com.dh.seriesservice.domain.models.Chapter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IChapterRepository extends MongoRepository<Chapter, ObjectId> {
}
