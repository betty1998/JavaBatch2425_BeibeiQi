package com.springboot.exception;

public class ProductNotFoundException extends ProductException {
//    Quantity of product equals 0
    public ProductNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
