package com.sprintstrickers.ecommerce.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

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

@RunWith(MockitoJUnitRunner.class)
public class PurchaseProductServiceImplTest {
	@InjectMocks
	PurchaseProductServiceImpl purchaseProductServiceImpl;
	
	@Mock
	UserRepository userRepository;
	
	@Mock
	ProductRepository productRepository;
	@Mock
	UserOrderRepository userOrderRepository;
	PurchaseProductRequestDto purchaseProductRequestDto=null;
	PurchaseProductResponseDto purchaseProductResponseDto=null;
	User user=null;
	Product product=null;
	UserOrder userOrder=null;
	@Before
	public void setUp() {
		purchaseProductRequestDto=new PurchaseProductRequestDto();
		purchaseProductRequestDto.setNoOfProducts(2);
		purchaseProductResponseDto=new PurchaseProductResponseDto();
		purchaseProductResponseDto.setMessage(ApiConstant.ORDERED_SUCCESS);
		purchaseProductResponseDto.setStatusCode(200);
		user=new User();
		user.setUserId(200);
		product=new Product();
		product.setProductName("mobile");
		product.setProductId(100);
		product.setProductBrand("vivo");
		product.setProductPrice(5000.55);
		userOrder=new UserOrder();
		userOrder.setUserId(user);
		userOrder.setUserOrderId(300);
	}
	@Test
	public void testPurchaseProduct() throws InvalidProduct, InvalidUser {
		Mockito.when(userRepository.findByUserId(200)).thenReturn(Optional.of(user));
		Mockito.when(productRepository.findByProductId(100)).thenReturn(Optional.of(product));
		Mockito.when(userOrderRepository.save(userOrder)).thenReturn(userOrder);
		PurchaseProductResponseDto response=purchaseProductServiceImpl.purchaseProduct(200, 100, purchaseProductRequestDto);
	assertEquals(ApiConstant.ORDERED_SUCCESS, response.getMessage());
	}
	@Test(expected =InvalidUser.class )
	public void testPurchaseProductForUserException() throws InvalidProduct, InvalidUser {
		Mockito.when(userRepository.findByUserId(400)).thenReturn(Optional.of(user));
		Mockito.when(productRepository.findByProductId(100)).thenReturn(Optional.of(product));
		Mockito.when(userOrderRepository.save(userOrder)).thenReturn(userOrder);
		purchaseProductServiceImpl.purchaseProduct(200, 100, purchaseProductRequestDto);
	}
	@Test(expected =InvalidProduct.class )
	public void testPurchaseProductForProductException() throws InvalidProduct, InvalidUser {
		Mockito.when(userRepository.findByUserId(200)).thenReturn(Optional.of(user));
		Mockito.when(productRepository.findByProductId(300)).thenReturn(Optional.of(product));
		Mockito.when(userOrderRepository.save(userOrder)).thenReturn(userOrder);
		purchaseProductServiceImpl.purchaseProduct(200, 100, purchaseProductRequestDto);
	}
}
