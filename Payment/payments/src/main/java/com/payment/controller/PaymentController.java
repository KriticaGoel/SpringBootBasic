package com.payment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payment.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {
	
	private PaymentService paymentService;
	
	public PaymentController(PaymentService paymentService) {
		this.paymentService=paymentService;
	}
	
	@GetMapping("/initiate/{order_Id}")
	public String generatePaymentLink(@PathVariable("order_Id") String orderId) {
		//TODO: call orderservice api to get order details
		
		return paymentService.generatePaymentLink(orderId);
		//logic to generate payment link
		//return "Payment link generate successfully for order id "+ orderId;
	}

}
