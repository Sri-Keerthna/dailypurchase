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

import com.sprintstrickers.ecommerce.entity.Product;
import com.sprintstrickers.ecommerce.exception.NoProductFoundException;
import com.sprintstrickers.ecommerce.service.ProductService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ProductControllerTest {

	@InjectMocks
	ProductController productController;

	@Mock
	ProductService productService;

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
		logger.info("Got the list of products");
		Mockito.when(productService.getProductList("aaa")).thenReturn(productList);
		HttpStatus result = productController.productList("aaa").getStatusCode();
		assertEquals(HttpStatus.OK, result);
	}
}
