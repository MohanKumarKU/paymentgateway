package com.innovativein48.paymentgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import com.innovativein48.paymentgateway.controllers.PaymentGatewayRestController;

@SpringBootApplication
@ComponentScan("com.innovativein48.paymentgateway")
public class PaymentgatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentgatewayApplication.class, args);
	}
	
	
}
