package com.innovativein48.paymentgateway;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.innovativein48.paymentgateway.controllers.PaymentGatewayRestController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentgatewayApplicationTests {

	@Test
	public void contextLoads() {
		
		PaymentGatewayRestController cont =new PaymentGatewayRestController();
		cont.doPayment1();
		
	}

}
