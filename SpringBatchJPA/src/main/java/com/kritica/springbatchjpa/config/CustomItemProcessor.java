package com.kritica.springbatchjpa.config;

import com.kritica.springbatchjpa.model.Product;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class CustomItemProcessor implements ItemProcessor<Product, Product> {
    @Override
    public Product process(Product item)  {
        Double discount = item.getPrice()*(item.getDiscount()/100.0);
        Double finalAmount= item.getPrice()-discount;
        item.setFinalAmount(finalAmount);

        return item;
    }
}