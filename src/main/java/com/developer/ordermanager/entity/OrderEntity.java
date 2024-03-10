package com.developer.ordermanager.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Builder
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderid")
    private Long id;

    @ElementCollection
    @Column(name = "menuitems", nullable = false)
    private List<Long> menuItems;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private UserEntity user;

    @Column(name = "Status", nullable = false)
    private String status;

    @Column(name = "TotalPrice", nullable = false)
    private BigDecimal totalPrice;


    @Column(name = "CompletionTime")
    private Long completionTime;
}
