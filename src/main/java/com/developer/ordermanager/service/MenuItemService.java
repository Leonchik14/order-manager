package com.developer.ordermanager.service;

import com.developer.ordermanager.entity.MenuItemEntity;

import java.util.List;
import java.util.Optional;

public interface MenuItemService {

    public List<MenuItemEntity> findAllMenuItems();

    public Optional<MenuItemEntity> findById(Long id);

    public MenuItemEntity update(MenuItemEntity menuItem);

    public MenuItemEntity save(MenuItemEntity menuItem);

    public void delete(Long id);
}
