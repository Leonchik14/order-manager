package com.developer.ordermanager.contoller;

import com.developer.ordermanager.dto.ReviewRequest;
import com.developer.ordermanager.entity.ReviewEntity;
import com.developer.ordermanager.service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("order_manager/review")
public class ReviewController {
    private final ReviewService _reviewService;

    public ReviewController(ReviewService reviewServiceImpl) {
        this._reviewService = reviewServiceImpl;
    }

    @GetMapping
    public List<ReviewEntity> findAllReviews() {
        return _reviewService.findAllReviews();
    }

    @GetMapping("/{id}")
    public Optional<ReviewEntity> findReviewById(@PathVariable("id") Long id) {
        return _reviewService.findById(id);
    }

    @PostMapping("/make_review")
    public ReviewEntity makeReview(@RequestBody ReviewRequest request, Principal principal) {
        return _reviewService.makeReview(request.getMenuitemid(), request.getRating(),
                request.getComment(), principal.getName());
    }

    @GetMapping("/average")
    public double getAverageRate() {
        return _reviewService.getAverageRate();
    }
}
