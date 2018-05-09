package com.innovativein48.paymentgateway.helper;

import java.util.Map;

import com.innovativein48.paymentgateway.bean.Status;
import com.innovativein48.paymentgateway.dto.ReservationRequest;
import com.innovativein48.paymentgateway.entities.Transactionlog;

public interface VerificationService {

	Transactionlog verifyLocation(Map<String, String> deviceLocation, Map<String, String> transactionLocation, ReservationRequest reservationRequest);

}