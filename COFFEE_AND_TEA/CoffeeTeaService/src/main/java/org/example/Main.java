package org.example;

import lombok.NonNull;
import org.example.services.CoffeeService;
import org.example.services.DrinkInterface;
import org.example.models.drink.DrinkType;
import org.example.services.TeaService;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static DrinkInterface drinkService;

    public static void main(String[] args) {
        switch (chooseDrinkType()) {
            case COFFEE:
                drinkService = new CoffeeService();
                break;
            case TEA:
                drinkService = new TeaService();
                break;
        }

        drinkService.order();
    }

    @NonNull
    public static DrinkType chooseDrinkType(){
        System.out.println("Welcome to Coffee and Tea");
        System.out.println("Would you like to order coffee or tea? (COFFEE/TEA)");

        try {
            return DrinkType.valueOf(scanner.nextLine().toUpperCase());
        }catch (IllegalArgumentException exp){
            System.out.println("Invalid size.");
            throw exp;
        }
    }
}