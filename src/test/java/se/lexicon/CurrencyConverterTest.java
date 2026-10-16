package se.lexicon;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import se.lexicon.CurrencyConverter.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CurrencyConverterTest {
   private static final double DELTA = 1e-9;

   @ParameterizedTest
   @CsvSource({
         "105, SEK, USD, 10",
         "10, USD, SEK, 105",
         "115, SEK, EUR, 10",
         "10, EUR, SEK, 115",
         "0, SEK, USD, 0"
   })
   void convert(double amount, Currency from, Currency to, double expected) {
      assertEquals(expected, CurrencyConverter.convert(amount, from, to), DELTA);
   }

   @ParameterizedTest
   @EnumSource(Currency.class)
   void convertingToSameCurrencyReturnsSameAmount(Currency currency) {
      assertEquals(42.5, CurrencyConverter.convert(42.5, currency, currency), DELTA);
   }

   @Test
   void convertingBackAndForthReturnsOriginalAmount() {
      double usd = CurrencyConverter.convert(250, Currency.SEK, Currency.USD);
      assertEquals(250, CurrencyConverter.convert(usd, Currency.USD, Currency.SEK), DELTA);
   }

   @Test
   void convertsBetweenTwoForeignCurrencies() {
      // 11.50 SEK per EUR / 10.50 SEK per USD
      assertEquals(11.50 / 10.50, CurrencyConverter.convert(1, Currency.EUR, Currency.USD), DELTA);
   }
}
