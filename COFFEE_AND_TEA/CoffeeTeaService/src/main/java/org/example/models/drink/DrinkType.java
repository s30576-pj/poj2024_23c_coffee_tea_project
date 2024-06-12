package org.example.models.drink;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DrinkType {
    COFFEE("Coffee"),
    TEA("Tea");

    private final String name;
}
