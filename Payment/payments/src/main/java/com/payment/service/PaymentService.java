package com.payment.service;

public interface PaymentService {
	// By default all method of interface are public
	String generatePaymentLink(String orderId);
	

}
