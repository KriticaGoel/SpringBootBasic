package com.kritica.springbatch.config;

import com.kritica.springbatch.model.Product;
import org.springframework.batch.item.ItemProcessor;

public class CustomItemProcessor implements ItemProcessor<Product, Product> {
    @Override
    public Product process(Product item)  {
        try {
            int discountper = Integer.parseInt(item.getDiscount().trim());
            Double price = Double.parseDouble(item.getPrice().trim());
            Double finalPrice = price - (price * (discountper / 100));
            item.setFinalPrice(String.valueOf(finalPrice));
            return item;
        }catch(NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return item;
    }
}
