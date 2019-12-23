package com.sprintstrickers.ecommerce.service;

import java.util.List;

import com.sprintstrickers.ecommerce.dto.OrderResponseDto;
import com.sprintstrickers.ecommerce.exception.NoOrdersFoundException;

/**
 * @author Sri Keerthna
 * @since 2019-12-23
 */
public interface UserOrderService {

	public List<OrderResponseDto> getOrderList(Integer userId) throws NoOrdersFoundException;
}
