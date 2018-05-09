package com.innovativein48.paymentgateway.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.innovativein48.paymentgateway.dto.ReservationRequest;
import com.innovativein48.paymentgateway.entities.Transactionlog;
import com.innovativein48.paymentgateway.entities.Users;
import com.innovativein48.paymentgateway.helper.VerificationService;
import com.innovativein48.paymentgateway.repos.CustomerRepository;
import com.innovativein48.paymentgateway.xmpp.DeviceLocator;

@RestController
public class PaymentGatewayRestController {
	@Autowired
	CustomerRepository customerRepo;
	@Autowired
	VerificationService locationVerification;
	
	@RequestMapping(value = "/payment", method = RequestMethod.POST)
	public @ResponseBody ReservationRequest doPayment(@RequestBody ReservationRequest reservationRequest) {
		
		System.out.println(reservationRequest.toString());

		Users customer = customerRepo.findById(Long.valueOf(reservationRequest.getCardNumber())).get();
		
		Map<String,String> deviceLocation = new DeviceLocator().getLocation(customer.getToken());
		
		Map<String,String> transactionLocation = new HashMap<>();
		transactionLocation.put(reservationRequest.getLatitude(), reservationRequest.getLongitude());
		
		Transactionlog result = locationVerification.verifyLocation(deviceLocation, transactionLocation,reservationRequest);
		reservationRequest.setReferenceId(result.getReferenceid());
		reservationRequest.setStatus(result.getStatus());

		System.out.println("customer token : " + customer.getToken());

		return reservationRequest;

	}

	@RequestMapping(value = "/payment1", method = RequestMethod.GET)
	public @ResponseBody ReservationRequest doPayment1() {

		System.out.println("customer token :1 ");
		System.out.println("customer token : 2");
		
		//Map<String,String> deviceLocation = new DeviceLocator().getLocation(null);

		
		// new DeviceLocator().getLocation("12");
		
		Map<String,String> deviceLocation1 = new HashMap<>();
		deviceLocation1.put("13.1111111", "75.11100011");
		
		Map<String,String> transactionLocation = new HashMap<>();
		transactionLocation.put("12.1111111", "76.11100011");
		
		//LocationVerification locationVerification = new LocationVerification(null);
		
		ReservationRequest reqst= new ReservationRequest();
		reqst.setAccountNumber(111l);
		Transactionlog result = locationVerification.verifyLocation(deviceLocation1, transactionLocation,reqst);
		

		
		
      // Users customer = customerRepo.findById(Long.valueOf("12345")).get();

		//System.out.println("customer token : " + customer.getToken());
		
		return new ReservationRequest();
	}

}
