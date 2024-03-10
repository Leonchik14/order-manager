package com.developer.ordermanager.repository;

import com.developer.ordermanager.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepo extends JpaRepository<ReviewEntity, Long> {
}
