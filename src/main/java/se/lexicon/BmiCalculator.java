package se.lexicon;

import java.util.Scanner;

public final class BmiCalculator {
   // WHO adult BMI category limits (upper bound, exclusive).
   private static final double UNDERWEIGHT_LIMIT = 18.5;
   private static final double NORMAL_LIMIT = 25.0;
   private static final double OVERWEIGHT_LIMIT = 30.0;

   private BmiCalculator() {
   }

   public static double calculateBmi(double weightKg, double heightCm) {
      double heightM = heightCm / 100;
      return weightKg / (heightM * heightM);
   }

   public static String getCategory(double bmi) {
      if (bmi < UNDERWEIGHT_LIMIT) {
         return "Underweight";
      } else if (bmi < NORMAL_LIMIT) {
         return "Normal weight";
      } else if (bmi < OVERWEIGHT_LIMIT) {
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
      return InputHelper.readDouble(scanner, prompt, value -> value > 0,
            "Value must be greater than 0. Please try again.");
   }
}
