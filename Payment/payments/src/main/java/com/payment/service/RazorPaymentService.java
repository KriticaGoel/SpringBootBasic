package com.payment.service;

import org.springframework.stereotype.Service;

@Service
public class RazorPaymentService implements PaymentService {

	@Override
	public String generatePaymentLink(String orderId) {
		return "RazorPay Not Implemeted";
	}

}
