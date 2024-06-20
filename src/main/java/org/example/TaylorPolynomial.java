package org.example;
import java.util.function.Function;

public class TaylorPolynomial {
    // Funzione per calcolare la derivata n-esima approssimativa
    public static Function<Double, Double> differentiate(Function<Double, Double> f, double h) {
        return (x) -> (f.apply(x + h) - f.apply(x - h)) / (2 * h);
    }

    // Funzione per calcolare il polinomio di Taylor
    public static double taylorPolynomial(Function<Double, Double> f, double a, double x, int n) {
        double result = 0.0;
        double h = 1e-5; // Passo per la differenziazione numerica
        Function<Double, Double> fn = f;

        for (int i = 0; i <= n; i++) {
            double term = fn.apply(a) * Math.pow(x - a, i) / factorial(i);
            result += term;
            fn = differentiate(fn, h);
        }

        return result;
    }

    // Funzione per calcolare il fattoriale
    public static long factorial(int n) {
        if (n == 0) {
            return 1;
        }
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static void main(String[] args) {
        // Esempio: approssimazione della funzione esponenziale e^x intorno al punto 0
        Function<Double, Double> expFunction = Math::exp;
        double a = 0.0;
        double x = 1.0;
        int n = 5;

        double taylorResult = taylorPolynomial(expFunction, a, x, n);
        System.out.println("Approssimazione del polinomio di Taylor di e^x a x = " + x + " è: " + taylorResult);
    }
}
