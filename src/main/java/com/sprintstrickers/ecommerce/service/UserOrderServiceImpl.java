package com.sprintstrickers.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprintstrickers.ecommerce.dto.OrderResponseDto;
import com.sprintstrickers.ecommerce.entity.User;
import com.sprintstrickers.ecommerce.entity.UserOrder;
import com.sprintstrickers.ecommerce.exception.NoOrdersFoundException;
import com.sprintstrickers.ecommerce.repository.UserOrderRepository;
import com.sprintstrickers.ecommerce.repository.UserRepository;
import com.sprintstrickers.ecommerce.utils.StringConstant;

/**
 * @author Sri Keerthna
 * @since 2019-12-23
 */
@Service
public class UserOrderServiceImpl implements UserOrderService {

	@Autowired
	UserOrderRepository orderRepository;
	
	@Autowired
	UserRepository userRepository;

	/**
	 * The Constant log.
	 */
	private static final Logger logger = LoggerFactory.getLogger(UserOrderServiceImpl.class);

	/**
	 * @author Sri Keerthna
	 * @since 2019-12-23. In this method if the user has ordered for any product
	 *        those list will be displayed.
	 * @param userId using user id the orders of that particular id will be
	 *               displayed
	 * @return list of orders will be displayed
	 * @throws NoOrdersFoundException if there is no orders found then it will throw
	 *                                an error
	 */
	@Override
	public List<OrderResponseDto> getOrderList(Integer userId) throws NoOrdersFoundException {
		logger.info("inside the get order list method");
		List<OrderResponseDto> responseDto=new ArrayList<>();
		Optional<User> userResponse=userRepository.findById(userId);
		if(userResponse.isPresent()) {
		List<UserOrder> orderList = orderRepository.findByUserId(userResponse.get());
		if (orderList.isEmpty()) {
			throw new NoOrdersFoundException(StringConstant.ORDERS_EXCEPTION);
		} else {
			orderList.forEach(order -> {
				OrderResponseDto orderResponseDto = new OrderResponseDto();
				BeanUtils.copyProperties(order, orderResponseDto);
				responseDto.add(orderResponseDto);
			});
			
		}
		}
		return responseDto;
	}

}
