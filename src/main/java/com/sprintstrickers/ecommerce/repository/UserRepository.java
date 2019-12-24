package com.sprintstrickers.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprintstrickers.ecommerce.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByUserId(Integer userId);

	Optional<User> findByEmail(String userEmail);

}
