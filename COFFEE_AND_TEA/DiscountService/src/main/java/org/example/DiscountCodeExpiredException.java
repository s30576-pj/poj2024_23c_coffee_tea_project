package org.example;

public class DiscountCodeExpiredException extends RuntimeException {
    public DiscountCodeExpiredException(String message) {
        super(message);
    }
}
