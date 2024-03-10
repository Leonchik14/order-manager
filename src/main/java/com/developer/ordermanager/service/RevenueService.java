package com.developer.ordermanager.service;

import com.developer.ordermanager.entity.RevenueEntity;

public interface RevenueService {

    public RevenueEntity save(RevenueEntity revenue);

    public RevenueEntity updateRevenue(RevenueEntity revenue);

    public RevenueEntity getRevenue();
}
