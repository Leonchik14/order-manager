package com.developer.ordermanager.repository;

import com.developer.ordermanager.entity.RevenueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RevenueRepo extends JpaRepository<RevenueEntity, Long> {
}
