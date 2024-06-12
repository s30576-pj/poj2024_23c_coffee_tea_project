package org.example.services;

import org.example.models.items.Items;

import java.util.List;

public interface DrinkInterface {
    default double order() {
        return calculatePrice(chooseSize(), chooseAdditions());
    }
    Items chooseSize();
    List<Items> chooseAdditions();
    double calculatePrice(Items cupSize, List<Items> additions);
}
