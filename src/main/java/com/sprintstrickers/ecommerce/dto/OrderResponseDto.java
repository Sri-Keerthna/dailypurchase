package com.sprintstrickers.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponseDto {

	private Integer userOrderId;
	private String productName;
	private Integer totalQuantity;
	private Double totalPrice;
	private Double productPrice;
}
