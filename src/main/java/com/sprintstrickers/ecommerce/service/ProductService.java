package com.sprintstrickers.ecommerce.service;

import java.util.List;

import com.sprintstrickers.ecommerce.entity.Product;
import com.sprintstrickers.ecommerce.exception.NoProductFoundException;

/**
 * @author Sri Keerthna
 * @since 2019-12-23
 */
public interface ProductService {

	public List<Product> getProductList(String productName) throws NoProductFoundException;
}
