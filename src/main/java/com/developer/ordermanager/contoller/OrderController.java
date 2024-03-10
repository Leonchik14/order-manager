package com.developer.ordermanager.contoller;

import com.developer.ordermanager.dto.OrderUpdateRequest;
import com.developer.ordermanager.entity.OrderEntity;
import com.developer.ordermanager.service.OrderService;
import com.developer.ordermanager.service.impl.OrderServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("order_manager/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<OrderEntity> findAllOrders() {
        return orderService.findAllOrders();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Optional<OrderEntity> findOrderById(@PathVariable("id") Long id) {
        return orderService.findById(id);
    }

    @PostMapping("/make_order")
    public OrderEntity makeOrder(@RequestBody List<Long> menuItems, Principal principal) {
        return orderService.makeOrder(menuItems, principal.getName());
    }

    @PostMapping("/{id}/pay")
    public void payOrder(@PathVariable("id") Long id, Principal principal) {
        orderService.payOrder(id, principal.getName());
    }

    @PutMapping
    public OrderEntity updateOrder(@RequestBody OrderUpdateRequest orderUpdateRequest, Principal principal) {
        return orderService.updateOrder(orderUpdateRequest, principal.getName());
    }

    @DeleteMapping ("/{id}")
    public void cancelOrder(@PathVariable("id") Long id, Principal principal) {
        orderService.cancelOrder(id, principal.getName());
    }

    @GetMapping("/count")
    public Long getOrdersCount() {
        return orderService.getOrdersCount();
    }
}
