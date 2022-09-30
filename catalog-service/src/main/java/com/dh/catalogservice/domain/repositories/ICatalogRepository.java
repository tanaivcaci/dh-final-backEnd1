package com.dh.catalogservice.domain.repositories;

import com.dh.catalogservice.domain.models.Catalog;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatalogRepository extends MongoRepository<Catalog, ObjectId> {
   Catalog findByGenre(String genre);

}
