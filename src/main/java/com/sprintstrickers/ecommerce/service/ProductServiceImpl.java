package com.sprintstrickers.ecommerce.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprintstrickers.ecommerce.entity.Product;
import com.sprintstrickers.ecommerce.exception.NoProductFoundException;
import com.sprintstrickers.ecommerce.repository.ProductRepository;
import com.sprintstrickers.ecommerce.utils.StringConstant;

/**
 * @author Sri Keerthna
 * @since 2019-12-23
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	/**
	 * The Constant log.
	 */
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	/**
	 * @author Sri Keerthna
	 * @since 2019-12-23 In this method input is given as product name using that
	 *        name related product names will be displayed in list
	 * @param productName is given as input
	 * @return list of products will be displayed
	 * @throws NoProductFoundException if no products found then it will throw an error
	 */
	@Override
	public List<Product> getProductList(String productName) throws NoProductFoundException {
		logger.info("inside the get product list method");
		List<Product> productList = productRepository.findProductByProductNameContains(productName);
		if(productList.isEmpty()) {
			throw new NoProductFoundException(StringConstant.PRODUCT_EXCEPTION);
		}
		else {
		return productList;
		}
	}

}
