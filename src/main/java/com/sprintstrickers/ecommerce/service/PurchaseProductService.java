package com.sprintstrickers.ecommerce.service;

import com.sprintstrickers.ecommerce.dto.PurchaseProductRequestDto;
import com.sprintstrickers.ecommerce.dto.PurchaseProductResponseDto;
import com.sprintstrickers.ecommerce.exception.InvalidProduct;
import com.sprintstrickers.ecommerce.exception.InvalidUser;

public interface PurchaseProductService {

	public PurchaseProductResponseDto purchaseProduct(Integer userId, Integer productId,PurchaseProductRequestDto purchaseProductRequestDto) throws InvalidProduct, InvalidUser;
}
