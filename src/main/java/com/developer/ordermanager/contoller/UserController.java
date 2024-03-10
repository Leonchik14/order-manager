package com.developer.ordermanager.contoller;

import com.developer.ordermanager.entity.UserEntity;
import com.developer.ordermanager.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("order_manager/user")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<UserEntity> findAllUsers() {
        return userServiceImpl.findAllEntities();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Optional<UserEntity> findUserById(@PathVariable("id") Long id) {
        return userServiceImpl.findById(id);
    }

    @PostMapping("/new_user")
    public UserEntity saveUser(@RequestBody UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userServiceImpl.saveEntity(user);
    }

}
