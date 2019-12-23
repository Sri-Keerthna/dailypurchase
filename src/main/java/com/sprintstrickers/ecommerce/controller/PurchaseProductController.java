package com.sprintstrickers.ecommerce.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sprintstrickers.ecommerce.dto.PurchaseProductRequestDto;
import com.sprintstrickers.ecommerce.dto.PurchaseProductResponseDto;
import com.sprintstrickers.ecommerce.dto.VerifyCreditCardRequestDto;
import com.sprintstrickers.ecommerce.dto.VerifyCreditCardResponseDto;
import com.sprintstrickers.ecommerce.exception.InvalidProduct;
import com.sprintstrickers.ecommerce.exception.InvalidUser;
import com.sprintstrickers.ecommerce.service.PurchaseProductService;
@RequestMapping("/users")
@RestController
public class PurchaseProductController {
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	PurchaseProductService purchaseProductService;
   
    @Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
    public static final Logger logger = LoggerFactory.getLogger(PurchaseProductController.class);
	/**
	 * @param userId
	 * @param productId
	 * @param purchaseProductRequestDto
	 * @return
	 * @throws InvalidProduct 
	 * @throws InvalidUser 
	 */
	@PostMapping("/{userId}/products/{productId}")
	public PurchaseProductResponseDto purchaseProduct(@PathVariable Integer userId, @PathVariable Integer productId,@RequestBody PurchaseProductRequestDto purchaseProductRequestDto) throws InvalidProduct, InvalidUser {
		PurchaseProductResponseDto
		
		purchaseProductResponseDto=purchaseProductService.purchaseProduct(userId,productId,purchaseProductRequestDto);
		return purchaseProductResponseDto;
	}
	/** 
	 * @param userId
	 * @param verifyCreditCardRequestDto
	 * @return response
	 */
	@PostMapping("{userId}/accounts/verify")
	public VerifyCreditCardResponseDto verify(@PathVariable Integer userId,@RequestBody VerifyCreditCardRequestDto verifyCreditCardRequestDto) {
		final String url="http://localhost:8082/mycredit/users/"+userId+"/accounts/verifies";
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		
		HttpEntity<VerifyCreditCardRequestDto> requestEntity = new HttpEntity<>(verifyCreditCardRequestDto,requestHeaders);
		VerifyCreditCardResponseDto	response= restTemplate
				.exchange(url, HttpMethod.POST, requestEntity,VerifyCreditCardResponseDto.class).getBody();
		return response;
	}
}
