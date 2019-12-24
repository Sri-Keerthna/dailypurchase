package com.sprintstrickers.ecommerce.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprintstrickers.ecommerce.dto.OrderResponseDto;
import com.sprintstrickers.ecommerce.exception.NoOrdersFoundException;
import com.sprintstrickers.ecommerce.service.UserOrderService;

/**
 * @author Sri Keerthna
 * @since 2019-12-23
 */
@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/users")
public class UserOrderController {

	@Autowired
	UserOrderService orderService;

	/**
	 * The Constant log.
	 */
	private static final Logger logger = LoggerFactory.getLogger(UserOrderController.class);

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
	@GetMapping("/{userId}/orders")
	public ResponseEntity<List<OrderResponseDto>> orderList(@Valid @PathVariable("userId") Integer userId)
			throws NoOrdersFoundException {
		logger.info("Got the list of products");
		List<OrderResponseDto> orderList = orderService.getOrderList(userId);
		return new ResponseEntity<>(orderList, HttpStatus.OK);
	}

}
