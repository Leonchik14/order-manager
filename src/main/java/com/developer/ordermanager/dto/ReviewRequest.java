package com.developer.ordermanager.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ReviewRequest {
    private Long menuitemid;
    private Integer rating;
    private String comment;
}
