package com.sprintstrickers.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprintstrickers.ecommerce.entity.UserOrder;

/**
 * @author Sri Keerthna
 * @since 2019-12-23
 */
@Repository
public interface UserOrderRepository extends JpaRepository<UserOrder, Integer> {

	/**
	 * @author Sri Keerthna
	 * @since 2019-12-23. In this method if the user has ordered for any product
	 *        those list will be displayed.
	 * @param userId using user id the orders of that particular id will be
	 *               displayed
	 * @return list of orders will be displayed
	 */

	List<UserOrder> findByUserUserId(Integer useId);


}
