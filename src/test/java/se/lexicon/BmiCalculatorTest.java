package se.lexicon;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BmiCalculatorTest {
   private static final double DELTA = 0.05;

   @ParameterizedTest
   @CsvSource({
         "70, 175, 22.9",
         "50, 180, 15.4",
         "85, 175, 27.8",
         "100, 170, 34.6",
         "100, 200, 25.0"
   })
   void calculateBmi(double weightKg, double heightCm, double expectedBmi) {
      assertEquals(expectedBmi, BmiCalculator.calculateBmi(weightKg, heightCm), DELTA);
   }

   @ParameterizedTest
   @CsvSource({
         "10.0, Underweight",
         "18.49, Underweight",
         "18.5, Normal weight",
         "24.99, Normal weight",
         "25.0, Overweight",
         "29.99, Overweight",
         "30.0, Obese",
         "45.0, Obese"
   })
   void getCategory(double bmi, String expectedCategory) {
      assertEquals(expectedCategory, BmiCalculator.getCategory(bmi));
   }
}
