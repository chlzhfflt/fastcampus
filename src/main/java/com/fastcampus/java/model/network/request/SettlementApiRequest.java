package com.fastcampus.java.model.network.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SettlementApiRequest {

    private Long id;

    private Long userId;

    private BigDecimal totalPrice;


}
