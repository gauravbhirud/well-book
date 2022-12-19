package com.mnt.wellbook.repository;

import com.mnt.wellbook.domain.Key;


import java.time.Instant;
import java.util.List;
import java.util.Optional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the {@link User} entity.
 */
@Repository
public interface KeyRepository extends JpaRepository<Key, Long> {

    
    Optional<Key> findOneByAlphanumericKey(String alphanumericKey);
    
    Key findOneByAlphanumericKeyIgnoreCase(String alphanumericKey);

	
}