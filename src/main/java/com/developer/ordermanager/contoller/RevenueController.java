package com.developer.ordermanager.contoller;

import com.developer.ordermanager.entity.RevenueEntity;
import com.developer.ordermanager.service.RevenueService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.security.authorization.AuthorityReactiveAuthorizationManager.hasAuthority;

@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("order_manager/revenue")
public class RevenueController {
    private final RevenueService revenueService;

    public RevenueController(RevenueService RevenueServiceImpl) {
        this.revenueService = RevenueServiceImpl;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public RevenueEntity getRevenue() {
        return revenueService.getRevenue();
    }


}
