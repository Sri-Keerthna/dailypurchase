package com.sprintstrickers.ecommerce.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.sprintstrickers.ecommerce.dto.OrderResponseDto;
import com.sprintstrickers.ecommerce.exception.NoOrdersFoundException;
import com.sprintstrickers.ecommerce.service.UserOrderService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserOrderControllerTest {

	@InjectMocks
	UserOrderController policyController;

	@Mock
	UserOrderService userOrderService;

	private static final Logger logger = LoggerFactory.getLogger(UserOrderController.class);

	static OrderResponseDto orderResponseDto = new OrderResponseDto();
	static List<OrderResponseDto> orderListDto = new ArrayList<>();
	
	@Before
	public void setup() {
		orderResponseDto.setProductName("aaa");
		orderResponseDto.setProductPrice(123D);
		orderResponseDto.setTotalPrice(2000D);
		orderResponseDto.setTotalQuantity(2);
		orderResponseDto.setUserOrderId(1);
		orderListDto.add(orderResponseDto);
	}
	
	@Test
	public void testOrderListPositive() throws NoOrdersFoundException {
		logger.info("Got the list of products");
		Mockito.when(userOrderService.getOrderList(1)).thenReturn(orderListDto);
		HttpStatus result=policyController.orderList(1).getStatusCode();
		assertEquals(HttpStatus.OK, result);
	}

}
