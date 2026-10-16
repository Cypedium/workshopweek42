package se.lexicon;

import java.util.Scanner;

public class CurrencyConverter {
   // Fixed example rates: how many SEK one unit of the foreign currency costs.
   public static final double SEK_PER_USD = 10.50;
   public static final double SEK_PER_EUR = 11.50;

   public static double sekToUsd(double sek) {
      return sek / SEK_PER_USD;
   }

   public static double usdToSek(double usd) {
      return usd * SEK_PER_USD;
   }

   public static double sekToEur(double sek) {
      return sek / SEK_PER_EUR;
   }

   public static double eurToSek(double eur) {
      return eur * SEK_PER_EUR;
   }

   public static void run(Scanner scanner) {
      do {
         System.out.println("--- Currency Converter ---");
         System.out.println("1. SEK to USD");
         System.out.println("2. USD to SEK");
         System.out.println("3. SEK to EUR");
         System.out.println("4. EUR to SEK");
         System.out.print("Choose an option: ");
         String choice = scanner.nextLine().trim();

         switch (choice) {
            case "1" -> {
               double sek = readAmount(scanner, "SEK");
               System.out.printf("%.2f SEK = %.2f USD%n", sek, sekToUsd(sek));
            }
            case "2" -> {
               double usd = readAmount(scanner, "USD");
               System.out.printf("%.2f USD = %.2f SEK%n", usd, usdToSek(usd));
            }
            case "3" -> {
               double sek = readAmount(scanner, "SEK");
               System.out.printf("%.2f SEK = %.2f EUR%n", sek, sekToEur(sek));
            }
            case "4" -> {
               double eur = readAmount(scanner, "EUR");
               System.out.printf("%.2f EUR = %.2f SEK%n", eur, eurToSek(eur));
            }
            default -> System.out.println("Invalid option. Please choose 1-4.");
         }
         System.out.println();
      } while (InputHelper.askYesNo(scanner, "Do you want to convert another amount? (yes/no): "));
   }

   private static double readAmount(Scanner scanner, String currency) {
      while (true) {
         double amount = InputHelper.readDouble(scanner, "Enter amount in " + currency + ": ");
         if (amount >= 0) {
            return amount;
         }
         System.out.println("Amount cannot be negative. Please try again.");
      }
   }
}
