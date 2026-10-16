package se.lexicon;

import java.util.Scanner;

public final class TemperatureConverter {
   // Nothing can be colder than absolute zero.
   public static final double ABSOLUTE_ZERO_CELSIUS = -273.15;
   public static final double ABSOLUTE_ZERO_FAHRENHEIT = -459.67;

   private TemperatureConverter() {
   }

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
         String choice = InputHelper.readLine(scanner, "Choose an option: ");

         switch (choice) {
            case "1" -> {
               double celsius = readTemperature(scanner, "Celsius", ABSOLUTE_ZERO_CELSIUS);
               System.out.printf("%.2f Celsius = %.2f Fahrenheit%n", celsius, celsiusToFahrenheit(celsius));
            }
            case "2" -> {
               double fahrenheit = readTemperature(scanner, "Fahrenheit", ABSOLUTE_ZERO_FAHRENHEIT);
               System.out.printf("%.2f Fahrenheit = %.2f Celsius%n", fahrenheit, fahrenheitToCelsius(fahrenheit));
            }
            default -> System.out.println("Invalid option. Please choose 1 or 2.");
         }
         System.out.println();
      } while (InputHelper.askYesNo(scanner, "Do you want to convert another temperature? (yes/no): "));
   }

   private static double readTemperature(Scanner scanner, String unit, double absoluteZero) {
      return InputHelper.readDouble(
            scanner,
            "Enter temperature in " + unit + ": ",
            value -> value >= absoluteZero,
            String.format("Temperature cannot be below absolute zero (%.2f %s).", absoluteZero, unit));
   }
}
