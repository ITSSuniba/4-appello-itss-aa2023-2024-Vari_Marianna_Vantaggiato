import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static  org.example.AdvancedApacheMathFunction.geometricMeanOfSumOfSquares;
import static org.example.AdvancedApacheMathFunction.geometricMeanOfSumOfSquaresVariableLength;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.IllegalArgumentException;
import java.lang.ArithmeticException;

public class LenghtTest {

    @Test
    public void testArrayWithDifferentLengths_ThrowsException() {
        double[] array1 = {1, 2};
        double[] array2 = {3, 4, 5};

        assertThrows(IllegalArgumentException.class, ()-> {
            geometricMeanOfSumOfSquares(array1, array2);
        });
    }

    @Test
    public void testOneEmptyArray() {
        double[] array1 = {1, 2, 3};
        double[] array2 = {};
        assertThrows(IllegalArgumentException.class, ()-> {
            geometricMeanOfSumOfSquares(array1, array2);
        });
    }

    @Test
    public void testArrayWithEqualLengths() {
        double[] array1 = {1, 2, 3};
        double[] array2 = {4, 5, 6};
        double expected = Math.sqrt((double) (1 + 16 + 4 + 25 + 9 + 36) / 3); // Calcolo manuale dell'output atteso
        double result = geometricMeanOfSumOfSquares(array1, array2);
        Assertions.assertEquals(expected, result, 0.01); // Verifica che il risultato ottenuto sia vicino all'output atteso con una tolleranza di 0.0001
    }

    @Test
    public void testArrayWithNegativeValues() {
        double[] array1 = {-1, -2, -3};
        double[] array2 = {-4, -5, -6};
        double result = geometricMeanOfSumOfSquares(array1, array2);
        assertTrue(result > 0); // Verifica che il risultato sia positivo
    }

    @Test
    public void testArrayWithMixValues() {
        double[] array1 = {-1, 2, -3};
        double[] array2 = {-4, -5, 6};
        double result = geometricMeanOfSumOfSquares(array1, array2);
        assertTrue(result > 0); // Verifica che il risultato sia positivo
    }

    @Test
    public void testArrayWithZeroValues() {
        double[] array1 = {0, 0, 0};
        double[] array2 = {0, 0, 0};
        double result = geometricMeanOfSumOfSquares(array1, array2);
        assertEquals(0, result, 0.0001); // Verifica che il risultato sia zero
    }

    @Test
    public void testArrayWithSingleElement() {
        double[] array1 = {3};
        double[] array2 = {4};
        double expected = Math.sqrt(3*3 + 4*4); // Calcolo manuale dell'output atteso
        double result = geometricMeanOfSumOfSquares(array1, array2);
        assertEquals(expected, result, 0.0001); // Verifica che il risultato ottenuto sia vicino all'output atteso con una tolleranza di 0.0001
    }
    @Test
    public void testGeometricMeanOfSumOfSquares_WithEmptyArrays_isNaN() {
        double[] array1 = {};
        double[] array2 = {};

        assertThrows(IllegalArgumentException.class, ()-> {
            geometricMeanOfSumOfSquares(array1, array2);
        });
    }

    @Test
    public void testArrayWithExtremeValues() {
        double[] array1 = {18056, 3, 0};
        double[] array2 = {0, -4, -51045};
        double result = geometricMeanOfSumOfSquares(array1, array2);
        assertEquals(31260.257, result, 0.001);
    }

}




