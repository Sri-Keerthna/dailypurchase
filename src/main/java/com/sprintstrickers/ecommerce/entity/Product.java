package com.sprintstrickers.ecommerce.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Sri Keerthna
 * @since 2019-12-23
 */
@Entity
@Getter
@Setter
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productId;
	private String productName;
	private String productBrand;
	private Double productPrice;
	private Integer availableQuantity;
	private String productDescription;

}
