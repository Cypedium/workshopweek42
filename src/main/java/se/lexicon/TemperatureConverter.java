package se.lexicon;

import java.util.Scanner;

public class TemperatureConverter {

   public static double celsiusToFahrenheit(double celsius) {
      return celsius * 9 / 5 + 32;
   }

   public static double fahrenheitToCelsius(double fahrenheit) {
      return (fahrenheit - 32) * 5 / 9;
   }

   public static void run(Scanner scanner) {
      do {
         System.out.println("--- Temperature Converter ---");
         System.out.println("1. Celsius to Fahrenheit");
         System.out.println("2. Fahrenheit to Celsius");
         System.out.print("Choose an option: ");
         String choice = scanner.nextLine().trim();

         switch (choice) {
            case "1" -> {
               double celsius = readTemperature(scanner, "Enter temperature in Celsius: ");
               System.out.printf("%.2f Celsius = %.2f Fahrenheit%n", celsius, celsiusToFahrenheit(celsius));
            }
            case "2" -> {
               double fahrenheit = readTemperature(scanner, "Enter temperature in Fahrenheit: ");
               System.out.printf("%.2f Fahrenheit = %.2f Celsius%n", fahrenheit, fahrenheitToCelsius(fahrenheit));
            }
            default -> System.out.println("Invalid option. Please choose 1 or 2.");
         }
         System.out.println();
      } while (askYesNo(scanner, "Do you want to convert another temperature? (yes/no): "));
   }

   private static boolean askYesNo(Scanner scanner, String prompt) {
      while (true) {
         System.out.print(prompt);
         String answer = scanner.nextLine().trim().toLowerCase();
         switch (answer) {
            case "yes", "y" -> {
               return true;
            }
            case "no", "n" -> {
               return false;
            }
            default -> System.out.println("Please answer yes or no.");
         }
      }
   }

   private static double readTemperature(Scanner scanner, String prompt) {
      while (true) {
         System.out.print(prompt);
         String input = scanner.nextLine().trim().replace(',', '.');
         try {
            return Double.parseDouble(input);
         } catch (NumberFormatException e) {
            System.out.println("Invalid number. Please try again.");
         }
      }
   }
}
