package com.sprintstrickers.ecommerce.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.sprintstrickers.ecommerce.controller.UserOrderController;
import com.sprintstrickers.ecommerce.dto.OrderResponseDto;
import com.sprintstrickers.ecommerce.entity.User;
import com.sprintstrickers.ecommerce.entity.UserOrder;
import com.sprintstrickers.ecommerce.exception.NoOrdersFoundException;
import com.sprintstrickers.ecommerce.repository.UserOrderRepository;
import com.sprintstrickers.ecommerce.repository.UserRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserOrderServiceTest {

	@InjectMocks
	UserOrderServiceImpl userOrderService;

	@Mock
	UserOrderRepository userOrderRepository;
	
	@Mock
	UserRepository userRepository;

	private static final Logger logger = LoggerFactory.getLogger(UserOrderController.class);

	static OrderResponseDto orderResponseDto = new OrderResponseDto();
	static List<OrderResponseDto> orderListDto = new ArrayList<>();
	static User user=new User();
	static UserOrder order=new UserOrder();
	static List<UserOrder> userList=new ArrayList<>();
	
	@Before
	public void setup() {
		user.setDob(LocalDate.of(2000, 9, 10));
		user.setEmail("ddd");
		user.setIncome(2000D);
		user.setUserId(1);
		
		order.setUserId(user);
		order.setUserOrderId(1);
		order.setProductPrice(2000D);
		userList.add(order);
	}
	
	@Test
	public void testGetOrderListPositive() throws NoOrdersFoundException {
		logger.info("inside the get order list method");
		Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));
		Mockito.when(userOrderRepository.findByUserId(user)).thenReturn(userList);
		BeanUtils.copyProperties(order, orderResponseDto);
		List<OrderResponseDto> result=userOrderService.getOrderList(1);
		assertEquals(1, result.size());
	}
	
	@Test(expected = NoOrdersFoundException.class)
	public void testGetOrderListNegative() throws NoOrdersFoundException {
		logger.info("inside the get order list method");
		List<UserOrder> usersLists=new ArrayList<>();
		Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));
		Mockito.when(userOrderRepository.findByUserId(user)).thenReturn(usersLists);
		userOrderService.getOrderList(1);
	}

}
