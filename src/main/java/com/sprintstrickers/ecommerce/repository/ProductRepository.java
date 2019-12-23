package com.sprintstrickers.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprintstrickers.ecommerce.entity.Product;

/**
 * @author Sri Keerthna
 * @since 2019-12-23
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	/**
	 * @author Sri Keerthna
	 * @since 2019-12-23
	 * In this method input is given as product name using that 
	 * name related product names will be displayed in list 
	 * @param productName is given as input
	 * @return list of products will be displayed
	 */
	List<Product> findProductByProductNameContains(String productName);

}
