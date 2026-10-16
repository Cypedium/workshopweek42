package se.lexicon;

import java.util.Scanner;

public class InputHelper {

   public static boolean askYesNo(Scanner scanner, String prompt) {
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

   public static double readDouble(Scanner scanner, String prompt) {
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
