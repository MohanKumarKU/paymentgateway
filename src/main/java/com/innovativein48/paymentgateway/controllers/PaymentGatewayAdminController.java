package com.innovativein48.paymentgateway.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.innovativein48.paymentgateway.entities.Transactionlog;
import com.innovativein48.paymentgateway.repos.TransactionLogRepository;

@Controller
public class PaymentGatewayAdminController {
	
	
	@Autowired
	TransactionLogRepository transactionLogRepo;
	
	@RequestMapping("admin")
	public String showAdminDashBoard() {
		return "adminPage";
	}
	
	
	@RequestMapping(value = "/getUserDetail", method = RequestMethod.POST)
	public String doPayment(@RequestParam("referenceId") String referenceId, ModelMap modelMap) {
		Transactionlog transactionlog = transactionLogRepo.findById(Long.valueOf(referenceId)).get();
		modelMap.addAttribute("transactionlog", transactionlog);
		return "adminInquiry";
	}

}
