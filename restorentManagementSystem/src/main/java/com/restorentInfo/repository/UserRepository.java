package com.restorentInfo.repository;

import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.restorentInfo.model.User;

@Repository
public interface UserRepository
        extends ElasticsearchRepository<User, String> {

    Optional<User> findByUsername(String username);
}