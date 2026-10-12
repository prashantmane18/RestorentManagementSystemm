package com.restorentInfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restorentInfo.model.Restorent;

@Repository
public interface RestoRepository
        extends JpaRepository<Restorent, String> {
}