package com.developer.ordermanager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@Builder
@Table(name = "revenue")
@AllArgsConstructor
@NoArgsConstructor
public class RevenueEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RevenueID")
    private Long revenueId;


    @Column(name = "TotalRevenue", nullable = false)
    private BigDecimal totalRevenue;

}
