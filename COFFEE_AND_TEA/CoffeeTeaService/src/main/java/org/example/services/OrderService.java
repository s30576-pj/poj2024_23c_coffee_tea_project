package org.example.services;

import org.example.models.drink.DrinkType;
import org.example.models.items.Items;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class OrderService {
    public void saveOrder(List<Items> selectedAdditions, double price, Items size, DrinkType drinkType) {
        try (FileWriter fw = new FileWriter("Orders.txt", true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.printf("Order: %s | Size: %s | Additions: %s | Final Price: %.2f\n",
                    drinkType.getName(), size, selectedAdditions, price);
        } catch (IOException e) {
            System.out.println("Error saving the order: " + e.getMessage());
        }
    }
}
