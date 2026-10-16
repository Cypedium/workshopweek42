package se.lexicon;

import java.util.Scanner;

public class Main {
   private static final String DIVIDER = "==================================";

   public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      boolean running = true;

      while (running) {
         printMenu();
         String choice = scanner.nextLine().trim();

         switch (choice) {
            case "1" -> TemperatureConverter.run(scanner);
            case "2" -> System.out.println("Currency Converter is not implemented yet.");
            case "3" -> System.out.println("BMI Calculator is not implemented yet.");
            case "4" -> {
               System.out.println("Goodbye!");
               running = false;
            }
            default -> System.out.println("Invalid option. Please choose 1-4.");
         }
         System.out.println();
      }
      scanner.close();
   }

   private static void printMenu() {
      System.out.println(DIVIDER);
      System.out.println("      LEXICON CONVERTER APP");
      System.out.println(DIVIDER);
      System.out.println("1. Temperature Converter");
      System.out.println("2. Currency Converter");
      System.out.println("3. BMI Calculator");
      System.out.println("4. Exit");
      System.out.println(DIVIDER);
      System.out.print("Choose an option: ");
   }
}
