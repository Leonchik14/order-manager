package com.developer.ordermanager.repository;

import com.developer.ordermanager.entity.OrderEntity;
import com.developer.ordermanager.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findOrderEntitiesByUserAndStatus(UserEntity user, String status);
}
