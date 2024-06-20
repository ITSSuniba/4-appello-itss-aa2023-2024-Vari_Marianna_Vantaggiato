import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import com.pholser.junit.quickcheck.Property;
import java.util.function.Function;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitQuickcheck.class)
public class TaylorPolynomialPropertyTest {
    // Tolleranza per il confronto dei valori in virgola mobile
    private static final double TOLERANCE = 1e-5;

    @Property
    public void testValueAtPointOfExpansion(double x) {
        Function<Double, Double> expFunction = Math::exp;
        double a = x;
        int n = 0;

        double expectedValue = expFunction.apply(a);
        double taylorResult = TaylorPolynomial.taylorPolynomial(expFunction, a, x, n);

        assertEquals("Il polinomio di Taylor di ordine zero deve restituire il valore della funzione al punto di espansione", expectedValue, taylorResult, TOLERANCE);
    }

    @Property
    public void testCosineSymmetry(double x) {
        Function<Double, Double> cosFunction = Math::cos;
        double a = 0.0;
        int n = 10;

        double taylorPositive = TaylorPolynomial.taylorPolynomial(cosFunction, a, x, n);
        double taylorNegative = TaylorPolynomial.taylorPolynomial(cosFunction, a, -x, n);

        assertEquals("Il polinomio di Taylor di cos(x) dovrebbe essere simmetrico", taylorPositive, taylorNegative, TOLERANCE);
    }

    @Property
    public void testLocalPrecision(double x) {
        Function<Double, Double> sinFunction = Math::sin;
        double a = 0.0;
        int n = 5;

        double expectedValue = sinFunction.apply(x);
        double taylorResult = TaylorPolynomial.taylorPolynomial(sinFunction, a, x, n);

        assertTrue("Il polinomio di Taylor dovrebbe essere più preciso vicino al punto di espansione",
                Math.abs(expectedValue - taylorResult) < 1.0);
    }
}
