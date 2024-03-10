package com.developer.ordermanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class OrderUpdateRequest {
    Long orderId;
    List<Long> menuitems;
}
