import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Report;
import net.jqwik.api.Reporting;
import net.jqwik.api.statistics.Histogram;
import net.jqwik.api.statistics.StatisticsReport;
import org.example.AdvancedApacheMathFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdvancedApacheMathFunctionPropertyDifferentLength {
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
