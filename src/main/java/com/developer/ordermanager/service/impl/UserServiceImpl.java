package com.developer.ordermanager.service.impl;

import com.developer.ordermanager.entity.UserEntity;
import com.developer.ordermanager.repository.UserRepo;
import com.developer.ordermanager.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<UserEntity> findAllEntities() {
        return userRepo.findAll();
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return userRepo.findById(id);
    }

    @Override
    public UserEntity saveEntity(UserEntity entity) {
        return userRepo.save(entity);
    }

}
