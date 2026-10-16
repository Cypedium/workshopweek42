package se.lexicon;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TemperatureConverterTest {
   private static final double DELTA = 1e-9;

   @ParameterizedTest
   @CsvSource({
         "0, 32",
         "100, 212",
         "-40, -40",
         "37, 98.6",
         "-273.15, -459.67"
   })
   void celsiusToFahrenheit(double celsius, double expectedFahrenheit) {
      assertEquals(expectedFahrenheit, TemperatureConverter.celsiusToFahrenheit(celsius), DELTA);
   }

   @ParameterizedTest
   @CsvSource({
         "32, 0",
         "212, 100",
         "-40, -40",
         "98.6, 37",
         "-459.67, -273.15"
   })
   void fahrenheitToCelsius(double fahrenheit, double expectedCelsius) {
      assertEquals(expectedCelsius, TemperatureConverter.fahrenheitToCelsius(fahrenheit), DELTA);
   }

   @Test
   void convertingBackAndForthReturnsOriginalValue() {
      double celsius = 21.5;
      double roundTrip = TemperatureConverter.fahrenheitToCelsius(TemperatureConverter.celsiusToFahrenheit(celsius));
      assertEquals(celsius, roundTrip, DELTA);
   }

   @Test
   void absoluteZeroConstantsMatchEachOther() {
      assertEquals(TemperatureConverter.ABSOLUTE_ZERO_FAHRENHEIT,
            TemperatureConverter.celsiusToFahrenheit(TemperatureConverter.ABSOLUTE_ZERO_CELSIUS), DELTA);
   }
}
