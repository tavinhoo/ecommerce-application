package com.ecommerce.productmanagement.exceptions;

public class ProductAlreadyExists extends RuntimeException {
    public ProductAlreadyExists(String msg) {
        super(msg);
    }
}
