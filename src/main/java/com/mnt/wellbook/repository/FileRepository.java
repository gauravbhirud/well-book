package com.mnt.wellbook.repository;


import java.time.Instant;
import java.util.List;
import java.util.Optional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mnt.wellbook.domain.Document;

/**
 * Spring Data JPA repository for the {@link User} entity.
 */
@Repository
public interface FileRepository extends JpaRepository<Document, Long> {
	
	
}