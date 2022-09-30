package com.dh.catalogservice.api.service;

import com.dh.catalogservice.domain.models.Catalog;


public interface ICatalogService {
   Catalog getCatalogByGenre (String genre);
   void updateCatalogByGenre(String genre);

}
