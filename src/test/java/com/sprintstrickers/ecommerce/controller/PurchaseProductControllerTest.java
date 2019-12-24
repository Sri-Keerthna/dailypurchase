package com.sprintstrickers.ecommerce.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import com.sprintstrickers.ecommerce.exception.InvalidProduct;
import com.sprintstrickers.ecommerce.exception.InvalidUser;
import com.sprintstrickers.ecommerce.service.PurchaseProductService;
import com.sprintstrickers.ecommerce.utils.ApiConstant;

@RunWith(MockitoJUnitRunner.class)
public class PurchaseProductControllerTest {
	@InjectMocks
	PurchaseProductController purchaseProductController;
	
	@Mock
	PurchaseProductService purchaseProductService;
	PurchaseProductRequestDto purchaseProductRequestDto=null;
	PurchaseProductResponseDto purchaseProductResponseDto=null;
	Product product=null;
	@Before
	public void setUp() {
		purchaseProductRequestDto=new PurchaseProductRequestDto();
		purchaseProductRequestDto.setNoOfProducts(2);
		purchaseProductResponseDto=new PurchaseProductResponseDto();
		purchaseProductResponseDto.setMessage(ApiConstant.ORDERED_SUCCESS);
		purchaseProductResponseDto.setStatusCode(200);
		product=new Product();
		product.setProductId(100);
	}
	@Test
	public void testPurchaseProduct() throws InvalidProduct, InvalidUser {
		Mockito.when(purchaseProductService.purchaseProduct(200, 100, purchaseProductRequestDto)).thenReturn(purchaseProductResponseDto);
		PurchaseProductResponseDto response=purchaseProductController.purchaseProduct(200, 100, purchaseProductRequestDto);
	assertEquals(ApiConstant.ORDERED_SUCCESS, response.getMessage());
	}
}
