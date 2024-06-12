package org.example.models.items;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TeaItems implements Items {
    // Sizes
    S("SMALL", 5.0, Type.SIZE),
    M("MEDIUM", 7.0, Type.SIZE),
    L("LARGE", 9.0, Type.SIZE),

    // Additions
    SUGAR("SUGAR", 0.2, Type.ADDITION),
    MINT("MINT", 0.3, Type.ADDITION),
    LEMON("LEMON", 0.6, Type.ADDITION),
    FLOWERS("FLOWERS", 0.7, Type.ADDITION);

    private final String name;
    private final double price;
    private final Type type;

    public enum Type {
        SIZE, ADDITION
    }
}
