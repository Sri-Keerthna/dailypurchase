package com.sprintstrickers.ecommerce.service;

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

import com.sprintstrickers.ecommerce.controller.UserOrderController;
import com.sprintstrickers.ecommerce.entity.Product;
import com.sprintstrickers.ecommerce.exception.NoProductFoundException;
import com.sprintstrickers.ecommerce.repository.ProductRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ProductServiceTest {

	@InjectMocks
	ProductServiceImpl productService;

	@Mock
	ProductRepository productRepository;

	private static final Logger logger = LoggerFactory.getLogger(UserOrderController.class);

	static Product product = new Product();
	static List<Product> productList = new ArrayList<>();

	@Before
	public void setup() {
		product.setProductName("aaa");
		product.setProductPrice(123D);
		productList.add(product);
	}

	@Test
	public void testGetProductListPositive() throws NoProductFoundException {
		logger.info("inside the get product list method");
		Mockito.when(productRepository.findProductByProductNameContains("aaa")).thenReturn(productList);
		List<Product> result = productService.getProductList("aaa");
		assertEquals(1, result.size());
	}

	@Test(expected = NoProductFoundException.class)
	public void testGetProductListNegative() throws NoProductFoundException {
		logger.info("inside the get product list method");
		List<Product> productLists = new ArrayList<>();
		Mockito.when(productRepository.findProductByProductNameContains("aaa")).thenReturn(productLists);
		productService.getProductList("aaa");
	}
}
