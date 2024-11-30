package com.kritica.springbatch.config;

import com.kritica.springbatch.model.Product;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;

public class CustomItemProcessor implements ItemProcessor<Product, Product> {
    @Override
    public Product process(Product item) throws Exception {
        int discountper = Integer.parseInt(item.getDiscount());
        Double price= Double.parseDouble(item.getPrice());
        Double finalPrice = price -(price * (discountper/100));
        item.setDiscountedPrice(String.valueOf(finalPrice));
        return item;
    }
}
