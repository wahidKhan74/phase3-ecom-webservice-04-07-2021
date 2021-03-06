package com.ecom.webservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.webservice.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>  {
	
	Optional<User> findByUserName(String userName);
}
