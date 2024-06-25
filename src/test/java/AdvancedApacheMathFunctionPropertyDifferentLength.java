import com.pholser.junit.quickcheck.generator.Size;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Report;
import net.jqwik.api.Reporting;
import net.jqwik.api.constraints.NotEmpty;
import net.jqwik.api.statistics.Histogram;
import net.jqwik.api.statistics.StatisticsReport;
import org.example.AdvancedApacheMathFunction;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdvancedApacheMathFunctionPropertyDifferentLength {
    @Property
    @Report(Reporting.GENERATED)
    @StatisticsReport(format = Histogram.class)
    public void testVariableLengthNonNegativity(@ForAll @NotEmpty double[] array1, @ForAll @NotEmpty double[] array2) {
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
    public void testVariableLengthZeroAdditionTwoArrays(@ForAll @NotEmpty double[] array1,@ForAll @NotEmpty double[] array2) {
        double result1 = AdvancedApacheMathFunction.geometricMeanOfSumOfSquaresVariableLength(array1, array2);

        int maxLengthA = Math.max(array1.length, array2.length);
        double[] zeros = new double[maxLengthA];

        for (int i = 0; i < zeros.length; i++) {
            zeros[i] = 0.0;
        }

        double sumOfSquares1 = 0.0;

        for (int i = 0; i < maxLengthA; i++) {
            double value1 = (i < array1.length) ? array1[i] : 0.0;
            double value2 = (i < array2.length) ? array2[i] : 0.0;
            double value3 = (i < zeros.length ) ? zeros[i] : 0.0;
            sumOfSquares1 += value1 * value1 + value2 * value2 + value3 * value3;
        }

        double meanOfSquares = sumOfSquares1 / maxLengthA;
        double result2 = Math.sqrt(meanOfSquares);


        assertEquals(result1, result2, "Aggiungere zero non deve cambiare il risultato");
    }

}
