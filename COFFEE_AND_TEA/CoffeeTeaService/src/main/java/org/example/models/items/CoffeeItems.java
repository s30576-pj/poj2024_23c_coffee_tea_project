package org.example.models.items;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CoffeeItems implements Items {
    // Sizes
    S("SMALL", 6.0, Type.SIZE),
    M("MEDIUM", 8.0, Type.SIZE),
    L("LARGE", 10.0, Type.SIZE),

    // Additions
    SUGAR("SUGAR", 0.2, Type.ADDITION),
    MILK("MILK", 0.4, Type.ADDITION),
    CARAMEL("CARAMEL", 0.8, Type.ADDITION),
    COCOA("COCOA", 0.9, Type.ADDITION);

    private final String name;
    private final double price;
    private final Type type;

    public enum Type {
        SIZE, ADDITION
    }
}
