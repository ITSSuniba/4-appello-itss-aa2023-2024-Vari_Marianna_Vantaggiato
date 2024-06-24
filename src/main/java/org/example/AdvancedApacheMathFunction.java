package org.example;

public class AdvancedApacheMathFunction {

    public static double geometricMeanOfSumOfSquares(double[] array1, double[] array2) {
        if (array1.length != array2.length) {
            throw new IllegalArgumentException("Le lunghezze degli array devono essere uguali");
        }
        if (array1.length == 0) {
            throw new IllegalArgumentException("Gli array non devono essere vuoti");
        }

        double sumOfSquares1 = 0.0;
        double sumOfSquares2 = 0.0;


        for (int i = 0; i < array1.length; i++) {
            sumOfSquares1 += array1[i] * array1[i] ;
            sumOfSquares2 += array2[i] * array2[i] ;
        }


        double meanOfSquares = (sumOfSquares1 + sumOfSquares2) / array1.length;

        if (meanOfSquares < 0){
            throw new IllegalArgumentException("La media geometrica deve essere non negativa");
        }
        return Math.sqrt(meanOfSquares);
    }


    public static double geometricMeanOfSumOfSquaresVariableLength(double[] array1, double[] array2) {
        int maxLength = Math.max(array1.length, array2.length);

        double sumOfSquares = 0.0;

        for (int i = 0; i < maxLength; i++) {
            double value1 = (i < array1.length) ? array1[i] : 0.0;
            double value2 = (i < array2.length) ? array2[i] : 0.0;
            sumOfSquares += value1 * value1 + value2 * value2;
        }

        double meanOfSquares = sumOfSquares / maxLength;
        return Math.sqrt(meanOfSquares);
    }

    public static void main(String[] args) {
        double[] array1 = {2.0, 3.0, 4.0};
        double[] array2 = {1.0, 5.0, 2.0};

        double resultEqualLength = geometricMeanOfSumOfSquares(array1, array2);
        System.out.println("Media geometrica della somma dei quadrati (uguali lunghezze): " + resultEqualLength);

        double[] array3 = {2.0, 3.0, 4.0, 5.0};
        double resultVariableLength = geometricMeanOfSumOfSquaresVariableLength(array1, array3);
        System.out.println("Media geometrica della somma dei quadrati (lunghezze diverse)  : " + resultVariableLength);
    }
}