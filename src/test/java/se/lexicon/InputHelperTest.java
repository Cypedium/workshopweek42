package se.lexicon;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InputHelperTest {
   private final PrintStream originalOut = System.out;
   private final ByteArrayOutputStream output = new ByteArrayOutputStream();

   @BeforeEach
   void captureOutput() {
      System.setOut(new PrintStream(output));
   }

   @AfterEach
   void restoreOutput() {
      System.setOut(originalOut);
   }

   private static Scanner scannerOf(String input) {
      return new Scanner(input);
   }

   @ParameterizedTest
   @ValueSource(strings = {"yes", "y", "YES", " Yes "})
   void askYesNoAcceptsYes(String answer) {
      assertTrue(InputHelper.askYesNo(scannerOf(answer + "\n"), "? "));
   }

   @ParameterizedTest
   @ValueSource(strings = {"no", "n", "NO", " No "})
   void askYesNoAcceptsNo(String answer) {
      assertFalse(InputHelper.askYesNo(scannerOf(answer + "\n"), "? "));
   }

   @Test
   void askYesNoRepeatsUntilValidAnswer() {
      assertTrue(InputHelper.askYesNo(scannerOf("maybe\n\ny\n"), "? "));
      assertEquals(2, output.toString().split("Please answer yes or no.", -1).length - 1);
   }

   @Test
   void readDoubleAcceptsDecimalComma() {
      assertEquals(98.6, InputHelper.readDouble(scannerOf("98,6\n"), "? "));
   }

   @Test
   void readDoubleSkipsInvalidInput() {
      assertEquals(5.0, InputHelper.readDouble(scannerOf("abc\nNaN\nInfinity\n\n5\n"), "? "));
      assertEquals(4, output.toString().split("Invalid number. Please try again.", -1).length - 1);
   }

   @Test
   void readDoubleWithValidationRepeatsUntilValid() {
      double value = InputHelper.readDouble(scannerOf("-1\n0\n3\n"), "? ", v -> v > 0, "Must be positive.");
      assertEquals(3.0, value);
      assertEquals(2, output.toString().split("Must be positive.", -1).length - 1);
   }
}
