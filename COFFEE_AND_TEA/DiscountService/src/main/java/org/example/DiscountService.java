package org.example;

import org.example.services.ReduceInterface;

public class DiscountService implements ReduceInterface {

    @Override
    public double getDiscount(String code) throws DiscountCodeException, DiscountCodeExpiredException{
        DiscountCodes discountCodes = DiscountCodes.checkCode(code);
        if (!discountCodes.getStatus().equalsIgnoreCase("active")) {
            throw new DiscountCodeExpiredException("Discount code has expired");
        }
        return discountCodes.getDiscount();
    }
}
