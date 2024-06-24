import com.pholser.junit.quickcheck.generator.Size;
import net.jqwik.api.*;
import net.jqwik.api.constraints.NotEmpty;
import net.jqwik.api.statistics.Histogram;
import net.jqwik.api.statistics.StatisticsReport;
import org.example.AdvancedApacheMathFunction;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class AdvancedApacheMathFunctionPropertyBasedTest {

    @Property
    @Report(Reporting.GENERATED)
    @StatisticsReport(format = Histogram.class)
    public void testEqualLengthThrowsException(@ForAll @Size(min = 1, max = 10000) double[] array1, @ForAll @Size(min = 1, max = 10000) double[] array2) {
        Assume.that(array1.length != array2.length);

        assertThrows(IllegalArgumentException.class, () ->
                        AdvancedApacheMathFunction.geometricMeanOfSumOfSquares(array1, array2),
                "Le lunghezze degli array devono essere uguali"
        );
    }

    @Property
    @Report(Reporting.GENERATED)
    @StatisticsReport(format = Histogram.class)
    public void testNonNegativity(@ForAll @NotEmpty @Size(min = 1, max = 10000) double[] array1) {
        // Generare array2 con la stessa lunghezza di array1
        double[] array2 = new double[array1.length];
        Random random = new Random();
        for (int i = 0; i < array2.length; i++) {
            array2[i] = random.nextDouble();
        }

        // Stampare gli array per il debug
        System.out.println("array1: " + Arrays.toString(array1));
        System.out.println("array2: " + Arrays.toString(array2));

        double result = AdvancedApacheMathFunction.geometricMeanOfSumOfSquares(array1, array2);
        assertTrue(result >= 0, "La media geometrica deve essere non negativa");
    }



    @Property
    @Report(Reporting.GENERATED)
    @StatisticsReport(format = Histogram.class)
    public void testSymmetry(@ForAll @NotEmpty @Size(min = 1, max = 10000) double[] array1) {
        // Generare array2 con la stessa lunghezza di array1
        double[] array2 = new double[array1.length];
        Random random = new Random();
        for (int i = 0; i < array2.length; i++) {
            array2[i] = random.nextDouble();
        }
        double result1 = AdvancedApacheMathFunction.geometricMeanOfSumOfSquares(array1, array2);
        double result2 = AdvancedApacheMathFunction.geometricMeanOfSumOfSquares(array2, array1);

        assertEquals(result1, result2, "La funzione deve essere simmetrica");
    }

    @Property
    @Report(Reporting.GENERATED)
    @StatisticsReport(format = Histogram.class)
    public void testZeroAddition(@ForAll @NotEmpty @Size(min = 1, max = 10) double[] array1) {
        // Generare array2 con la stessa lunghezza di array1
        double[] array2 = new double[array1.length];
        Random random = new Random();
        for (int i = 0; i < array1.length; i++) {
            array2[i] = random.nextDouble();
        }

        double result1 = AdvancedApacheMathFunction.geometricMeanOfSumOfSquares(array1, array2);

        double[] zeros = new double[array1.length];

        for (int i = 0; i < array1.length; i++) {
            zeros[i] = 0.0;
        }

        double sumOfSquares1 = 0.0;
        double sumOfSquares2 = 0.0;
        double sumOfSquares3 = 0.0;


        for (int i = 0; i < array1.length; i++) {
            sumOfSquares1 += (array1[i] * array1[i]);
            sumOfSquares2 += (array2[i] * array2[i]);
            sumOfSquares3 += (zeros[i] * zeros[i]);
        }


        double result2 = Math.sqrt((sumOfSquares2 + sumOfSquares1 + sumOfSquares3)/(array1.length));


        assertEquals(result1, result2, "Aggiungere zero non deve cambiare il risultato");
    }

    @Property
    @Report(Reporting.GENERATED)
    @StatisticsReport(format = Histogram.class)
    public void testVariableLengthNonNegativity(@ForAll double[] array1, @ForAll double[] array2) {
        double result = AdvancedApacheMathFunction.geometricMeanOfSumOfSquaresVariableLength(array1, array2);
        assertTrue(result >= 0, "La media geometrica deve essere non negativa anche con lunghezze variabili");
    }

    @Property
    @Report(Reporting.GENERATED)
    @StatisticsReport(format = Histogram.class)
    public void testVariableLengthSymmetry(@ForAll double[] array1, @ForAll double[] array2) {
        double result1 = AdvancedApacheMathFunction.geometricMeanOfSumOfSquaresVariableLength(array1, array2);
        double result2 = AdvancedApacheMathFunction.geometricMeanOfSumOfSquaresVariableLength(array2, array1);

        assertEquals(result1, result2, "La funzione deve essere simmetrica anche con lunghezze variabili");
    }

    @Property
    @Report(Reporting.GENERATED)
    @StatisticsReport(format = Histogram.class)
    public void testVariableLengthZeroAddition(@ForAll double[] array1, @ForAll double[] array2) {
        double[] zeros = new double[Math.max(array1.length, array2.length)];

        double result1 = AdvancedApacheMathFunction.geometricMeanOfSumOfSquaresVariableLength(array1, zeros);
        double result2 = AdvancedApacheMathFunction.geometricMeanOfSumOfSquaresVariableLength(array1, array2);

        assertEquals(result1, result2, "Aggiungere zero non deve cambiare il risultato anche con lunghezze variabili");
    }

}
