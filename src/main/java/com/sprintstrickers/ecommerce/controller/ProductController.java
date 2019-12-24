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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sprintstrickers.ecommerce.entity.Product;
import com.sprintstrickers.ecommerce.exception.NoProductFoundException;
import com.sprintstrickers.ecommerce.service.ProductService;

/**
 * @author Sri Keerthna
 * @since 2019-12-23
 */
@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductService productService;

	/**
	 * The Constant log.
	 */
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	/**
	 * @author Sri Keerthna
	 * @since 2019-12-23. 
	 * In this method input is given as product name using that
	 *        name related product names will be displayed in list
	 * @param productName is given as input
	 * @return list of products will be displayed
	 * @throws NoProductFoundException if no products found then 
	 * it will throw an error
	 */
	@GetMapping
	public ResponseEntity<List<Product>> productList(@Valid @RequestParam("productName") String productName)
			throws NoProductFoundException {
		logger.info("Got the list of products");
		List<Product> productList = productService.getProductList(productName);
		return new ResponseEntity<>(productList, HttpStatus.OK);
	}

}
