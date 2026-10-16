package se.lexicon;

import java.util.Scanner;

public class BmiCalculator {

   public static double calculateBmi(double weightKg, double heightCm) {
      double heightM = heightCm / 100;
      return weightKg / (heightM * heightM);
   }

   // WHO adult BMI categories.
   public static String getCategory(double bmi) {
      if (bmi < 18.5) {
         return "Underweight";
      } else if (bmi < 25) {
         return "Normal weight";
      } else if (bmi < 30) {
         return "Overweight";
      }
      return "Obese";
   }

   public static void run(Scanner scanner) {
      do {
         System.out.println("--- BMI Calculator ---");
         double weightKg = readPositive(scanner, "Enter your weight in kg: ");
         double heightCm = readPositive(scanner, "Enter your height in cm: ");
         double bmi = calculateBmi(weightKg, heightCm);
         System.out.printf("Your BMI is %.1f (%s)%n", bmi, getCategory(bmi));
         System.out.println();
      } while (InputHelper.askYesNo(scanner, "Do you want to calculate another BMI? (yes/no): "));
   }

   private static double readPositive(Scanner scanner, String prompt) {
      while (true) {
         double value = InputHelper.readDouble(scanner, prompt);
         if (value > 0) {
            return value;
         }
         System.out.println("Value must be greater than 0. Please try again.");
      }
   }
}
