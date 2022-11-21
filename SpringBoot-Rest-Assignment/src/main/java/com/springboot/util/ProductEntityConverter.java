package com.springboot.util;

import com.springboot.entity.ProductEntity;
import com.springboot.vo.Product;

public class ProductEntityConverter {
    public static Product convertEntityToProduct(ProductEntity productEntity){
        if (productEntity != null) {
            Product product = new Product();
            product.setId(productEntity.getId());
            product.setName(productEntity.getName());
            product.setQuantity(productEntity.getQuantity());
            product.setPrice(productEntity.getPrice());
            System.out.println(product);
            return product;
        } else {
            return null;
        }
    }
}
