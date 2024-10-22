package com.pl.premierzone.repositories;

import com.pl.premierzone.entities.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, String> {
    Optional<PlayerEntity> findByName(String name);
}
