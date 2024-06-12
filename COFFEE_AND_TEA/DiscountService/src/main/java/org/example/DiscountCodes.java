package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DiscountCodes {
    TEA_5("TEA_5", 0.05, "active"),
    TEA_10("TEA_10", 0.10, "active"),
    BLACK_FRIDAY("BLACK_FRIDAY15", 0.15, "expired");

    private final String code;
    private final double discount;
    private final String status;

    public static DiscountCodes checkCode(String code) throws DiscountCodeException{
        for (DiscountCodes discountCode : DiscountCodes.values()) {
            if (discountCode.getCode().equals(code)) {
                return discountCode;
            }
        }
        throw new DiscountCodeException("Invalid discount code.");
    }
}
