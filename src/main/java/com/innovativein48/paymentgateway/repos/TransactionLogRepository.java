package com.innovativein48.paymentgateway.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innovativein48.paymentgateway.entities.TransactionLogs;


public interface TransactionLogRepository extends JpaRepository<TransactionLogs, Long>{

}
