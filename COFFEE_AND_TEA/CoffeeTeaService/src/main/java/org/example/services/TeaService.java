package org.example.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.DiscountCodeException;
import org.example.DiscountCodeExpiredException;
import org.example.DiscountService;
import org.example.models.drink.DrinkType;
import org.example.models.items.Items;
import org.example.models.items.TeaItems;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@RequiredArgsConstructor
public class TeaService implements DrinkInterface {
    private OrderService orderService = new OrderService();
    private ReduceInterface discountService = new DiscountService();
    private double discount = 0.0;
    Scanner scanner = new Scanner(System.in);


    @Override
    public Items chooseSize() {
        System.out.print("Choose tea size (e.g. S, M or L): \n");
        String result = Arrays.stream(TeaItems.values())
                .filter(size -> size.getType() == TeaItems.Type.SIZE)
                .map(addition -> "Name: " + addition.getName() + " Price: " + addition.getPrice())
                .collect(Collectors.joining(", \n"));

        System.out.println(result);
        return TeaItems.valueOf(scanner.nextLine().toUpperCase());
    }

    @Override
    public List<Items> chooseAdditions() {
        System.out.println("Choose tea additions (enter additive names separated by commas, e.g. sugar, mint):");
        String result = Arrays.stream(TeaItems.values())
                .filter(additions -> additions.getType() == TeaItems.Type.ADDITION)
                .map(addition -> "Name: " + addition.getName() + " Price: " + addition.getPrice())
                .collect(Collectors.joining(", \n"));

        System.out.println(result);

        try {
            String[] choices = scanner.nextLine().toUpperCase().split(",");
            return Arrays.stream(choices)
                    .map(String::trim)
                    .map(name -> Arrays.stream(TeaItems.values())
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

        System.out.println("Total price for tea: " + String.format("%.2f", price));

        orderService.saveOrder(additions, price, cupSize, DrinkType.TEA);
        return 0;
    }
}
