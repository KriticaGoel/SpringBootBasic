package com.payment.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;

@Service
@Primary
public class StripePaymentService implements PaymentService {

	@Value("${stripe.secret.key}")
	private String secretKey;

	@Override
	public String generatePaymentLink(String orderId) {

		Stripe.apiKey = secretKey;
		try {

			ProductCreateParams params = ProductCreateParams.builder().setName("Gold Plan").build();
			Product product = Product.create(params);
//
//			PriceCreateParams priceParams = PriceCreateParams.builder().setCurrency("inr").setUnitAmount(1000L)
//					.setProduct(product.getId()).build();

			
			PriceCreateParams priceParams =
					  PriceCreateParams.builder()
					    .setCurrency("inr")
					    .setUnitAmount(10000L)  //10.00   if amount is 100.68 its hould come as 10068
					    .setProduct(product.getId())
					    .build();

					Price price = Price.create(priceParams);
			

					PaymentLinkCreateParams paymentParams =
							  PaymentLinkCreateParams.builder()
							    .addLineItem(
							      PaymentLinkCreateParams.LineItem.builder()
							        .setPrice(price.getId())
							        .setQuantity(1L)
							        .build()
							    )
							    .setAfterCompletion(
							      PaymentLinkCreateParams.AfterCompletion.builder()
							        .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
							        .setRedirect(
							          PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
							            .setUrl("https://google.com")
							            .build()
							        )
							        .build()
							    )
							    .build();

							PaymentLink paymentLink = PaymentLink.create(paymentParams);
			return paymentLink.getUrl();
		} catch (StripeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error";
		}

	}

}
