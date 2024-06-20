import com.pholser.junit.quickcheck.generator.Size;
import net.jqwik.api.*;
import net.jqwik.api.statistics.Histogram;
import net.jqwik.api.statistics.StatisticsReport;
import org.example.AdvancedApacheMathFunction;

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
    public void testNonNegativity(@ForAll @Size(min = 1, max = 10000) double[] array1, @ForAll @Size(min = 1, max = 10000) double[] array2) {
        Assume.that(array1.length == array2.length);

        double result = AdvancedApacheMathFunction.geometricMeanOfSumOfSquares(array1, array2);
        assertTrue(result >= 0, "La media geometrica deve essere non negativa");
    }

    @Property
    @Report(Reporting.GENERATED)
    @StatisticsReport(format = Histogram.class)
    public void testSymmetry(@ForAll @Size(min = 1, max = 10000) double[] array1, @ForAll @Size(min = 1, max = 10000) double[] array2) {
        Assume.that(array1.length == array2.length);

        double result1 = AdvancedApacheMathFunction.geometricMeanOfSumOfSquares(array1, array2);
        double result2 = AdvancedApacheMathFunction.geometricMeanOfSumOfSquares(array2, array1);

        assertEquals(result1, result2, "La funzione deve essere simmetrica");
    }

    @Property
    @Report(Reporting.GENERATED)
    @StatisticsReport(format = Histogram.class)
    public void testZeroAddition(@ForAll @Size(min = 1, max = 10000) double[] array1, @ForAll @Size(min = 1, max = 10000) double[] array2) {
        Assume.that(array1.length == array2.length);

        double[] zeros = new double[array1.length];

        double result1 = AdvancedApacheMathFunction.geometricMeanOfSumOfSquares(array1, zeros);
        double result2 = AdvancedApacheMathFunction.geometricMeanOfSumOfSquares(array1, array2);

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
