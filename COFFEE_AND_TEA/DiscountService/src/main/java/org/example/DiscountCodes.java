package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DiscountCodes {
    TEA10("TEA10", 0.10, "active"),
    TEA5("TEA5", 0.05, "active"),
    WINTERTEA15("WTEA15", 0.15, "expired");

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
