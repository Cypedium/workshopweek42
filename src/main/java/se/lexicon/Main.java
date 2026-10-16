package se.lexicon;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
   private static final String DIVIDER = "==================================";

   public static void main(String[] args) {
      try (Scanner scanner = new Scanner(System.in)) {
         runMenu(scanner);
      } catch (NoSuchElementException e) {
         // Input stream was closed (e.g. Ctrl+D / Ctrl+Z), so there is nothing more to read.
         System.out.println();
         System.out.println("Input closed. Goodbye!");
      }
   }

   private static void runMenu(Scanner scanner) {
      while (true) {
         printMenu();
         String choice = InputHelper.readLine(scanner, "Choose an option: ");

         switch (choice) {
            case "1" -> TemperatureConverter.run(scanner);
            case "2" -> CurrencyConverter.run(scanner);
            case "3" -> BmiCalculator.run(scanner);
            case "4" -> {
               System.out.println("Goodbye!");
               return;
            }
            default -> System.out.println("Invalid option. Please choose 1-4.");
         }
         System.out.println();
      }
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
   }
}
