package com.sprintstrickers.ecommerce.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sprintstrickers.ecommerce.dto.CreditCardDetailsDto;
import com.sprintstrickers.ecommerce.dto.CreditCardDetailsResponseDto;
import com.sprintstrickers.ecommerce.dto.CreditCardRequestDto;
import com.sprintstrickers.ecommerce.dto.PurchaseProductRequestDto;
import com.sprintstrickers.ecommerce.dto.PurchaseProductResponseDto;
import com.sprintstrickers.ecommerce.entity.Product;
import com.sprintstrickers.ecommerce.entity.User;
import com.sprintstrickers.ecommerce.entity.UserOrder;
import com.sprintstrickers.ecommerce.exception.InvalidProduct;
import com.sprintstrickers.ecommerce.exception.InvalidUser;
import com.sprintstrickers.ecommerce.repository.ProductRepository;
import com.sprintstrickers.ecommerce.repository.UserOrderRepository;
import com.sprintstrickers.ecommerce.repository.UserRepository;
import com.sprintstrickers.ecommerce.utils.ApiConstant;

/**
 * @author Naresh
 *
 */
@Service
public class PurchaseProductServiceImpl implements PurchaseProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserOrderRepository userOrderRepository;

	public static final Logger logger = LoggerFactory.getLogger(PurchaseProductServiceImpl.class);

	/**
	 * @throws InvalidProduct
	 * @throws InvalidUser
	 *
	 */
	@Override
	public PurchaseProductResponseDto purchaseProduct(Integer userId, Integer productId,
			PurchaseProductRequestDto purchaseProductRequestDto) throws InvalidProduct, InvalidUser {

		logger.info("Entering into the purchaseProduct method in PurchaseProductServiceImpl");
		PurchaseProductResponseDto purchaseProductResponseDto = null;
		Optional<User> optionalUser = userRepository.findByUserId(userId);
		if (optionalUser.isPresent()) {
			Optional<Product> optionalProduct = productRepository.findByProductId(productId);
			if (optionalProduct.isPresent()) {
				UserOrder order = new UserOrder();
				purchaseProductResponseDto = new PurchaseProductResponseDto();
				order.setUserId(optionalUser.get());
				order.setProductName(optionalProduct.get().getProductName());
				order.setProductPrice(optionalProduct.get().getProductPrice());
				Double price = optionalProduct.get().getProductPrice();
				Integer noOfProducts = purchaseProductRequestDto.getNoOfProducts();
				order.setTotalQuantity(purchaseProductRequestDto.getNoOfProducts());
				Double totalPrice = noOfProducts * price;
				order.setTotalPrice(totalPrice);
				userOrderRepository.save(order);
				purchaseProductResponseDto.setMessage(ApiConstant.ORDERED_SUCCESS);
				purchaseProductResponseDto.setStatusCode(201);
			} else {
				logger.info(
						"Throwing the product not found exception in purchaseProduct method in PurchaseProductServiceImpl ");
				throw new InvalidProduct(ApiConstant.PRODUCT_NOT_FOUND);
			}
		} else {
			logger.info(
					"Throwing the user not found exception in purchaseProduct method in PurchaseProductServiceImpl ");
			throw new InvalidUser(ApiConstant.USER_NOT_FOUND);
		}
		return purchaseProductResponseDto;
	}

}
