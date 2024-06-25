import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.example.AdvancedApacheMathFunction.geometricMeanOfSumOfSquares;
import static org.example.AdvancedApacheMathFunction.geometricMeanOfSumOfSquaresVariableLength;
import static org.junit.jupiter.api.Assertions.*;

public class DifferentLenghtTest {

    @Test
    public void testEmptyArrays() {
        double[] array1 = {};
        double[] array2 = {};

        double result = geometricMeanOfSumOfSquaresVariableLength(array1, array2);

        assertTrue(Double.isNaN(result)); // Verifica se il risultato è NaN

    }

    @Test
    public void testOneEmptyArray() {
        double[] array1 = {1, 2, 3};
        double[] array2 = {};
        double result = geometricMeanOfSumOfSquaresVariableLength(array1, array2);
        assertEquals(2.160, result, 0.001); // Adjust the expected result as per calculation
    }

    @Test
    public void testSingleElementArrays() {
        double[] array1 = {4};
        double[] array2 = {3};
        double result = geometricMeanOfSumOfSquaresVariableLength(array1, array2);
        assertEquals(5, result, 0.001); // Adjust the expected result as per calculation
    }

    @Test
    public void testOneArraySingleElement() {
        double[] array1 = {3, 4, 5};
        double[] array2 = {6};
        double result = geometricMeanOfSumOfSquaresVariableLength(array1, array2);
        assertEquals(5.35, result, 0.01); // Adjust the expected result as per calculation
    }

    @Test
    public void testArrayWithEqualLengths() {
        double[] array1 = {1, 2, 3};
        double[] array2 = {4, 5, 6};
        double expected = Math.sqrt((double) (1 + 16 + 4 + 25 + 9 + 36) / 3); // Calcolo manuale dell'output atteso
        double result = geometricMeanOfSumOfSquaresVariableLength(array1, array2);
        Assertions.assertEquals(expected, result, 0.01); // Verifica che il risultato ottenuto sia vicino all'output atteso con una tolleranza di 0.0001
    }

    @Test
    public void testArrayWithNegativeValues() {
        double[] array1 = {-1, -2, -3};
        double[] array2 = {-4, -5};
        double result = geometricMeanOfSumOfSquaresVariableLength(array1, array2);
        assertTrue(result > 0); // Verifica che il risultato sia positivo
    }

    @Test
    public void testArrayWithZeroValues() {
        double[] array1 = {0, 0, 0};
        double[] array2 = {0, 0};
        double result = geometricMeanOfSumOfSquaresVariableLength(array1, array2);
        assertEquals(0, result, 0.0001); // Verifica che il risultato sia zero
    }

    @Test
    public void testArrayWithMixedValues() {
        double[] array1 = {19, 3, 0};
        double[] array2 = {-4, 5};
        double result = geometricMeanOfSumOfSquaresVariableLength(array1, array2);
        assertEquals(11.704, result, 0.001);
    }

    @Test
    public void testArrayWithMixedValuesEqualLenght() {
        double[] array1 = {19, 3, 0};
        double[] array2 = {0, -4, 5};
        double result = geometricMeanOfSumOfSquaresVariableLength(array1, array2);
        assertEquals(11.704, result, 0.001); // Verifica che il risultato il valore atteso
    }

    @Test
    public void testArrayWithExtremeValues() {
        double[] array1 = {18056, 3};
        double[] array2 = {0, -4, -51045};
        double result = geometricMeanOfSumOfSquaresVariableLength(array1, array2);
        assertEquals(31260.257, result, 0.001);
    }




}
