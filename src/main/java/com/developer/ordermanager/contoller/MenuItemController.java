package com.developer.ordermanager.contoller;

import com.developer.ordermanager.entity.MenuItemEntity;
import com.developer.ordermanager.service.impl.MenuItemServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("order_manager/menuitem")
public class MenuItemController {
    private final MenuItemServiceImpl menuItemServiceImpl;

    public MenuItemController(MenuItemServiceImpl menuItemServiceImpl) {
        this.menuItemServiceImpl = menuItemServiceImpl;
    }

    @GetMapping("/show_menu")
    public List<MenuItemEntity> findAllMenuItems() {
        return menuItemServiceImpl.findAllMenuItems();
    }

    @GetMapping("/find/{id}")
    public Optional<MenuItemEntity> findMenuItemById(@PathVariable("id") Long id) {
        return menuItemServiceImpl.findById(id);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public MenuItemEntity saveMenuItem(@RequestBody MenuItemEntity menuItemEntity) {
        return menuItemServiceImpl.save(menuItemEntity);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ADMIN')")
    public MenuItemEntity updateMenuItem(@RequestBody MenuItemEntity menuItemEntity) {
        return menuItemServiceImpl.update(menuItemEntity);
    }

    @DeleteMapping ("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteMenuItem(@PathVariable("id") Long id) {
        menuItemServiceImpl.delete(id);
    }
}
