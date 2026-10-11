package com.restorentInfo.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.restorentInfo.model.Restorent;

@Repository
public interface RestoRepository
        extends ElasticsearchRepository<Restorent, String> {
}