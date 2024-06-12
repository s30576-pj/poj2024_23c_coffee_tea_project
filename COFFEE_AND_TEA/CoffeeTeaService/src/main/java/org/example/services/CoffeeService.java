package org.example.services;

import lombok.Getter;
import lombok.Setter;
import org.example.models.drink.DrinkType;
import org.example.models.items.CoffeeItems;
import org.example.models.items.Items;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
public class CoffeeService implements DrinkInterface {
    private OrderService orderService = new OrderService();
    Scanner scanner = new Scanner(System.in);

    @Override
    public Items chooseSize() {
        System.out.print("Choose coffee size (e.g. S, M or L): \n");
        String result = Arrays.stream(CoffeeItems.values())
                .filter(size -> size.getType() == CoffeeItems.Type.SIZE)
                .map(addition -> "Name: " + addition.getName() + " Price: " + addition.getPrice())
                .collect(Collectors.joining(", \n"));

        System.out.println(result);
        return CoffeeItems.valueOf(scanner.nextLine().toUpperCase());
    }


    @Override
    public List<Items> chooseAdditions() {
        System.out.println("Choose coffee additions (enter additive names separated by commas, e.g. sugar, milk):");
        String result = Arrays.stream(CoffeeItems.values())
                .filter(additions -> additions.getType() == CoffeeItems.Type.ADDITION)
                .map(addition -> "Name: " + addition.getName() + " Price: " + addition.getPrice())
                .collect(Collectors.joining(", \n"));

        System.out.println(result);

        try {
            String[] choices = scanner.nextLine().toUpperCase().split(",");
            return Arrays.stream(choices)
                    .map(String::trim)
                    .map(name -> Arrays.stream(CoffeeItems.values())
                            .filter(additions -> Objects.equals(additions.getName(), name))
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("Invalid addition number: " + name)))
                    .collect(Collectors.toList());
        } catch (NumberFormatException exp) {
            System.out.println("Invalid input. Please enter numbers separated by commas.");
        } catch (IllegalArgumentException exp) {
            System.out.println(exp.getMessage());
        }
        return Collections.emptyList();
    }

    @Override
    public double calculatePrice(Items cupSize, List<Items> additions) {
        double price = cupSize.getPrice();

        double additionsPrice = additions.stream()
                .mapToDouble(Items::getPrice)
                .sum();
        price += additionsPrice;

        System.out.println("Total price for coffee: " + String.format("%.2f", price));

        orderService.saveOrder(additions, price, cupSize, DrinkType.COFFEE);
        return 0;
    }
}
