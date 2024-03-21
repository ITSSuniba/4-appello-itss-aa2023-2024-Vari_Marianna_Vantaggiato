import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.example.AdvancedApacheMathFunction.geometricMeanOfSumOfSquaresVariableLength;
import static  org.example.AdvancedApacheMathFunction.geometricMeanOfSumOfSquares;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LenghtTest {
    @Test
    void testArrayWithDifferentLengths() {
        double[] array1 = {1, 2};
        double[] array2 = {3, 4, 5};
        double result = geometricMeanOfSumOfSquaresVariableLength(array1, array2);

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


}
