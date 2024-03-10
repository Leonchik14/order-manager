package com.developer.ordermanager.repository;

import com.developer.ordermanager.entity.MenuItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuItemRepo extends JpaRepository<MenuItemEntity, Long> {
    public Optional<MenuItemEntity> findMenuItemEntitiesByName(String name);
}
