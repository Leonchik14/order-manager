package com.developer.ordermanager.service;

import com.developer.ordermanager.entity.ReviewEntity;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    public List<ReviewEntity> findAllReviews();

    public Optional<ReviewEntity> findById(Long id);
    public ReviewEntity makeReview(Long menuItemId, Integer rating, String comment, String username);

    public double getAverageRate();
}
