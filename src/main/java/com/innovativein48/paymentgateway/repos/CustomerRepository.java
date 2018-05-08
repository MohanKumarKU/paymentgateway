package com.innovativein48.paymentgateway.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.innovativein48.paymentgateway.entities.Users;

public interface CustomerRepository extends JpaRepository<Users, Long> {
	@Query("SELECT t FROM Users t WHERE t.id= ?1")
	Users findUser(Long id);

}
