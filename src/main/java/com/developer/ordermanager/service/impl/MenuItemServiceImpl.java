package com.developer.ordermanager.service.impl;

import com.developer.ordermanager.entity.MenuItemEntity;
import com.developer.ordermanager.exception.IncorrectIdException;
import com.developer.ordermanager.exception.IncorrectMenuItemException;
import com.developer.ordermanager.repository.MenuItemRepo;
import com.developer.ordermanager.service.MenuItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepo menuItemRepo;

    public MenuItemServiceImpl(MenuItemRepo menuItemRepo) {
        this.menuItemRepo = menuItemRepo;
    }

    @Override
    public List<MenuItemEntity> findAllMenuItems() {
        return menuItemRepo.findAll();
    }

    @Override
    public Optional<MenuItemEntity> findById(Long id) {
        return menuItemRepo.findById(id);
    }

    @Override
    public MenuItemEntity save(MenuItemEntity entity) {
        return menuItemRepo.save(entity);
    }

    @Override
    public MenuItemEntity update(MenuItemEntity entity) {
        if (menuItemRepo.findMenuItemEntitiesByName(entity.getName()).isEmpty())
            throw new IncorrectMenuItemException("You are trying to update nonexistent menuitem");
        entity.setId(menuItemRepo.findMenuItemEntitiesByName(entity.getName()).get().getId());
        return menuItemRepo.save(entity);
    }

    @Override
    public void delete(Long id) {
        if (menuItemRepo.findById(id).isEmpty())
            throw new IncorrectIdException("You are trying to delete nonexistent menuitem");
        menuItemRepo.deleteById(id);
    }
}
