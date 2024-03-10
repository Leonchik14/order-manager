package com.developer.ordermanager.service.impl;

import com.developer.ordermanager.entity.OrderEntity;
import com.developer.ordermanager.entity.ReviewEntity;
import com.developer.ordermanager.entity.UserEntity;
import com.developer.ordermanager.exception.IllegalReviewException;
import com.developer.ordermanager.exception.IncorrectRatingException;
import com.developer.ordermanager.repository.MenuItemRepo;
import com.developer.ordermanager.repository.OrderRepo;
import com.developer.ordermanager.repository.ReviewRepo;
import com.developer.ordermanager.repository.UserRepo;
import com.developer.ordermanager.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepo _reviewRepo;
    private final MenuItemRepo _menuItemRepo;
    private final UserRepo _userRepo;
    
    private final OrderRepo _orderRepo;

    public ReviewServiceImpl(ReviewRepo reviewRepo, MenuItemRepo menuItemRepo, OrderRepo orderRepo, UserRepo userRepo) {
        this._reviewRepo = reviewRepo;
        this._menuItemRepo = menuItemRepo;
        this._orderRepo = orderRepo;
        this._userRepo = userRepo;
    }

    @Override
    public List<ReviewEntity> findAllReviews() {
        return _reviewRepo.findAll();
    }

    @Override
    public Optional<ReviewEntity> findById(Long id) {
        return _reviewRepo.findById(id);
    }

    @Override
    public ReviewEntity makeReview(Long menuItemId, Integer rating, String comment, String username) {
        UserEntity user = _userRepo.findByUsername(username).get();
        List<OrderEntity> payed = _orderRepo.findOrderEntitiesByUserAndStatus(user, "Оплачен");
        if (rating < 0 || rating > 5) throw new IncorrectRatingException("You entered incorrect mark");
        for (OrderEntity order : payed) {
            for (Long id: order.getMenuItems()) {
                if (Objects.equals(id, menuItemId)) {
                    return  _reviewRepo.save(ReviewEntity.builder()
                            .rating(rating)
                            .user(user)
                            .rating(rating)
                            .menuItem(_menuItemRepo.findById(menuItemId).get())
                            .comment(comment)
                            .build());
                }
            }
        }
        throw new IllegalReviewException("You are trying to rate dish that you didn't order");
    }

    @Override
    public double getAverageRate() {
        List<ReviewEntity> reviewEntities = _reviewRepo.findAll();
        if (reviewEntities.isEmpty()) return 0;
        return (double) reviewEntities.stream()
                .mapToInt(ReviewEntity::getRating)
                .sum() / (long) reviewEntities.size();
    }
}
