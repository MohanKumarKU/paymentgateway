package com.innovativein48.paymentgateway.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.innovativein48.paymentgateway.bean.Status;
import com.innovativein48.paymentgateway.dto.ReservationRequest;
import com.innovativein48.paymentgateway.entities.Users;
import com.innovativein48.paymentgateway.helper.LocationVerification;
import com.innovativein48.paymentgateway.repos.CustomerRepository;
import com.innovativein48.paymentgateway.xmpp.DeviceLocator;

@RestController
public class PaymentGatewayRestController {
	@Autowired
	CustomerRepository customerRepo;

	@RequestMapping(value = "/payment", method = RequestMethod.POST)
	public @ResponseBody ReservationRequest doPayment(@RequestBody ReservationRequest reservationRequest ) {
		
		System.out.println(reservationRequest.toString());

		Users customer = customerRepo.findById(Long.valueOf(reservationRequest.getCardNumber())).get();
		
		Map<String,String> deviceLocation = new DeviceLocator().getLocation(customer.getToken());
		
		Map<String,String> transactionLocation = new HashMap<>();
		transactionLocation.put(reservationRequest.getLatitude(), reservationRequest.getLongitude());
		
		LocationVerification locationVerification = new LocationVerification(reservationRequest);
		
		Status result = locationVerification.verifyLocation(deviceLocation, transactionLocation);
		

		System.out.println("customer token : " + customer.getToken());

		return null;

	}

	@RequestMapping(value = "/payment1", method = RequestMethod.GET)
	public @ResponseBody ReservationRequest doPayment1() {

		System.out.println("customer token :1 ");
		System.out.println("customer token : 2");

		
		// new DeviceLocator().getLocation("12");
		
		Map<String,String> deviceLocation = new HashMap<>();
		deviceLocation.put("13.1111111", "75.11100011");
		
		Map<String,String> transactionLocation = new HashMap<>();
		transactionLocation.put("12.1111111", "76.11100011");
		
		LocationVerification locationVerification = new LocationVerification(null);
		
		Status result = locationVerification.verifyLocation(deviceLocation, transactionLocation);
		

		
		
      // Users customer = customerRepo.findById(Long.valueOf("12345")).get();

		//System.out.println("customer token : " + customer.getToken());
		
		return new ReservationRequest();
	}

}
