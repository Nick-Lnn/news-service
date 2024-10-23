package com.dharbor.newsservice.model.repository;

import com.dharbor.newsservice.model.domain.Bulletin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BulletinRepository extends JpaRepository<Bulletin, Long> {

    @Query("SELECT b FROM Bulletin b WHERE b.isDeleted = false AND (:content IS NULL OR LOWER(b.body) LIKE LOWER(CONCAT('%', :content, '%')))")
    Page<Bulletin> findBulletins(@Param("content") String content, Pageable pageable);

}
