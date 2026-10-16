package se.lexicon;

import java.util.Scanner;

public final class CurrencyConverter {

   // Fixed example rates: how many SEK one unit of each currency costs.
   public enum Currency {
      SEK(1.0),
      USD(10.50),
      EUR(11.50);

      private final double sekPerUnit;

      Currency(double sekPerUnit) {
         this.sekPerUnit = sekPerUnit;
      }
   }

   private CurrencyConverter() {
   }

   public static double convert(double amount, Currency from, Currency to) {
      return amount * from.sekPerUnit / to.sekPerUnit;
   }

   public static void run(Scanner scanner) {
      do {
         System.out.println("--- Currency Converter ---");
         System.out.println("1. SEK to USD");
         System.out.println("2. USD to SEK");
         System.out.println("3. SEK to EUR");
         System.out.println("4. EUR to SEK");
         String choice = InputHelper.readLine(scanner, "Choose an option: ");

         switch (choice) {
            case "1" -> convertAndPrint(scanner, Currency.SEK, Currency.USD);
            case "2" -> convertAndPrint(scanner, Currency.USD, Currency.SEK);
            case "3" -> convertAndPrint(scanner, Currency.SEK, Currency.EUR);
            case "4" -> convertAndPrint(scanner, Currency.EUR, Currency.SEK);
            default -> System.out.println("Invalid option. Please choose 1-4.");
         }
         System.out.println();
      } while (InputHelper.askYesNo(scanner, "Do you want to convert another amount? (yes/no): "));
   }

   private static void convertAndPrint(Scanner scanner, Currency from, Currency to) {
      double amount = InputHelper.readDouble(
            scanner,
            "Enter amount in " + from + ": ",
            value -> value >= 0,
            "Amount cannot be negative. Please try again.");
      System.out.printf("%.2f %s = %.2f %s%n", amount, from, convert(amount, from, to), to);
   }
}
