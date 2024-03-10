package com.developer.ordermanager.service.impl;

import com.developer.ordermanager.dto.OrderUpdateRequest;
import com.developer.ordermanager.entity.MenuItemEntity;
import com.developer.ordermanager.entity.OrderEntity;
import com.developer.ordermanager.entity.RevenueEntity;
import com.developer.ordermanager.entity.UserEntity;
import com.developer.ordermanager.exception.IncorrectIdException;
import com.developer.ordermanager.exception.IncorrectQuantityException;
import com.developer.ordermanager.exception.IncorrectStatusException;
import com.developer.ordermanager.exception.IncorrectUserException;
import com.developer.ordermanager.repository.MenuItemRepo;
import com.developer.ordermanager.repository.OrderRepo;
import com.developer.ordermanager.repository.RevenueRepo;
import com.developer.ordermanager.repository.UserRepo;
import com.developer.ordermanager.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepo _orderRepo;
    private final MenuItemRepo _menuItemRepo;

    private final RevenueRepo _revenueRepo;

    private final UserRepo _userRepo;
    ExecutorService executorService = Executors.newFixedThreadPool(10);

    public OrderServiceImpl(OrderRepo orderRepo, MenuItemRepo menuItemRepo,
                            UserRepo userRepo, RevenueRepo revenueRepo) {
        this._orderRepo = orderRepo;
        this._menuItemRepo = menuItemRepo;
        this._userRepo = userRepo;
        this._revenueRepo = revenueRepo;
    }

    @Override
    public List<OrderEntity> findAllOrders() {
        return _orderRepo.findAll();
    }

    @Override
    public Optional<OrderEntity> findById(Long id) {
        return _orderRepo.findById(id);
    }

    @Override
    @Transactional
    public void processOrder(OrderEntity orderEntity) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                orderEntity.setStatus("Готовится");
                _orderRepo.save(orderEntity);
                try {
                    Thread.sleep(orderEntity.getCompletionTime() * 100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                orderEntity.setStatus("Готов");
                _orderRepo.save(orderEntity);
            }
        });
    }
    @Override
    public OrderEntity makeOrder(List<Long> menuItemIds, String userName) {
        BigDecimal price = BigDecimal.valueOf(0);
        Long completionTime = 0L;
        for (Long id: menuItemIds) {
            Optional<MenuItemEntity> menuItem = _menuItemRepo.findById(id);
            if (menuItem.isEmpty()) throw new IncorrectIdException("Attempt to order nonexistent diss");
            if (menuItem.get().getQuantity() == 0)
                throw new IncorrectQuantityException("Unfortunately we ran out of this dish");
            menuItem.get().setQuantity(menuItem.get().getQuantity() - 1);
            _menuItemRepo.save(menuItem.get());
            price = price.add(menuItem.get().getPrice());
            completionTime += menuItem.get().getCookingTime();
        }
        OrderEntity order = OrderEntity.builder()
                .status("Принят")
                .totalPrice(price)
                .completionTime(completionTime)
                .menuItems(menuItemIds)
                .user(_userRepo.findByUsername(userName).get())
                .build();
        processOrder(order);
        return _orderRepo.save(order);
    }

    @Override
    public OrderEntity updateOrder(OrderUpdateRequest request, String username) {
        UserEntity user = _userRepo.findByUsername(username).get();
        if (_orderRepo.findById(request.getOrderId()).isEmpty())
            throw new IncorrectIdException("You are trying to update nonexistent order");
        if (_orderRepo.findById(request.getOrderId()).get().getUser() != user)
            throw new IncorrectUserException("You are trying to update someone else's order");
        if (Objects.equals(_orderRepo.findById(request.getOrderId()).get().getStatus(), "Готов"))
            throw new IncorrectStatusException("You are trying to change finished order");
        return makeOrder(request.getMenuitems(), username);
    }

    @Override
    public void cancelOrder(Long id, String userName) {
        Optional<OrderEntity> toCancel = _orderRepo.findById(id);
        if (!toCancel.isPresent()) throw new IncorrectIdException("You are trying to cancel nonexistent order");
        if (Objects.equals(toCancel.get().getStatus(), "Готов"))
            throw new IncorrectStatusException("You are trying to cancel finished order");
        if (!Objects.equals(toCancel.get().getUser().getUsername(), userName))
            throw new IncorrectUserException("You are trying to cancel order that is not yours");
        _orderRepo.deleteById(id);
    }

    @Override
    public void payOrder(Long id, String username) {
        Optional<OrderEntity> toPay = _orderRepo.findById(id);
        if (toPay.isEmpty()) throw new IncorrectIdException("You are trying to pay for nonexistent order");
        if (!Objects.equals(toPay.get().getUser().getUsername(), username))
            throw new IncorrectUserException("You are trying to pay for someone else's order");
        if (!Objects.equals(toPay.get().getStatus(), "Готов"))
            throw new IncorrectStatusException("You are trying to pay an order with incorrect status");
        toPay.get().setStatus("Оплачен");
        List<RevenueEntity> revenue = _revenueRepo.findAll();
        if (revenue.isEmpty()) {
            revenue = List.of(RevenueEntity.builder().totalRevenue(BigDecimal.valueOf(0)).build());
        }
            revenue.getFirst().setTotalRevenue(revenue.getFirst().getTotalRevenue().add(toPay.get().getTotalPrice()));
        _revenueRepo.save(revenue.getFirst());
    }

    @Override
    public Long getOrdersCount() {
        return _orderRepo.findAll().isEmpty() ? 0L  : (long) _orderRepo.findAll().size() ;
    }

}
