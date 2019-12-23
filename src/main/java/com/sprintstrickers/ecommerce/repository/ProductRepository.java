package com.sprintstrickers.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprintstrickers.ecommerce.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	Optional<Product> findByProductId(Integer productId);

	List<Product> findProductByProductNameContains(String productName);

}
