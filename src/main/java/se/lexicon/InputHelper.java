package se.lexicon;

import java.util.Locale;
import java.util.Scanner;
import java.util.function.DoublePredicate;

public final class InputHelper {

   private InputHelper() {
   }

   public static String readLine(Scanner scanner, String prompt) {
      System.out.print(prompt);
      return scanner.nextLine().trim();
   }

   public static boolean askYesNo(Scanner scanner, String prompt) {
      while (true) {
         String answer = readLine(scanner, prompt).toLowerCase(Locale.ROOT);
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

   public static double readDouble(Scanner scanner, String prompt) {
      while (true) {
         String input = readLine(scanner, prompt).replace(',', '.');
         try {
            double value = Double.parseDouble(input);
            // parseDouble accepts "NaN" and "Infinity", which are not usable numbers here.
            if (Double.isFinite(value)) {
               return value;
            }
         } catch (NumberFormatException ignored) {
            // Fall through to the error message below.
         }
         System.out.println("Invalid number. Please try again.");
      }
   }

   public static double readDouble(Scanner scanner, String prompt, DoublePredicate isValid, String errorMessage) {
      while (true) {
         double value = readDouble(scanner, prompt);
         if (isValid.test(value)) {
            return value;
         }
         System.out.println(errorMessage);
      }
   }
}
