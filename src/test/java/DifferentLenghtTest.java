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



}
