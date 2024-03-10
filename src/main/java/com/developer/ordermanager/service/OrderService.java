package com.developer.ordermanager.service;

import com.developer.ordermanager.dto.OrderUpdateRequest;
import com.developer.ordermanager.entity.OrderEntity;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    public List<OrderEntity> findAllOrders();

    public Optional<OrderEntity> findById(Long id);

    public OrderEntity makeOrder(List<Long> menuItems, String username);

    public OrderEntity updateOrder(OrderUpdateRequest orderUpdateRequest, String username);

    public void processOrder(OrderEntity orderEntity);

    public void cancelOrder(Long id, String username);

    public void payOrder(Long id, String username);

    public Long getOrdersCount();

}
