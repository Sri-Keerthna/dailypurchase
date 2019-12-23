package com.sprintstrickers.ecommerce.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Sri Keerthna
 * @since 2019-12-23
 */

@Entity
@Getter
@Setter
public class UserOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userOrderId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", nullable = false)
	private User user;
	
	private String productName;
	private Integer totalQuantity;
	private Double totalPrice;
	private Double productPrice;
}
