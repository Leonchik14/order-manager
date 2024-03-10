package com.developer.ordermanager.service.impl;

import com.developer.ordermanager.entity.RevenueEntity;
import com.developer.ordermanager.repository.RevenueRepo;

import com.developer.ordermanager.service.RevenueService;
import org.springframework.stereotype.Service;

@Service
public class RevenueServiceImpl implements RevenueService {

    private final RevenueRepo revenueRepo;

    public RevenueServiceImpl(RevenueRepo revenueRepo) {
        this.revenueRepo = revenueRepo;
    }

    @Override
    public RevenueEntity getRevenue() {
        return revenueRepo.findAll().getFirst();
    }


    @Override
    public RevenueEntity save(RevenueEntity entity) {
        return revenueRepo.save(entity);
    }

    @Override
    public RevenueEntity updateRevenue(RevenueEntity entity) {
        return revenueRepo.save(entity);
    }

}
