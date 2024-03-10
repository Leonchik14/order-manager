package com.developer.ordermanager.service;

import com.developer.ordermanager.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {


    public List<UserEntity> findAllEntities();

    public Optional<UserEntity> findById(Long id);

    public UserEntity saveEntity(UserEntity entity);

}
