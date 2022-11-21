package com.springboot.exception;

public class ProductNotAvailableException extends ProductException {

    public ProductNotAvailableException(String errorMessage) {
        super(errorMessage);
    }
}
