package com.developer.ordermanager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@Data
@Table(name = "reviews")
@AllArgsConstructor
@NoArgsConstructor
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReviewID")
    private Long reviewId;

    @ManyToOne
    @JoinColumn(name = "MenuItemID", nullable = false)
    private MenuItemEntity menuItem;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private UserEntity user;

    @Column(name = "Rating", nullable = false)
    private Integer rating;

    @Column(name = "Comment")
    private String comment;
}
