package com.ecommerce.productmanagement.exceptions;

public class ProductNotFound extends RuntimeException {
    public ProductNotFound(String msg) {
        super(msg);
    }
}
