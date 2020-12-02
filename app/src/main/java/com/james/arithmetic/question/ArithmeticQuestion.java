package com.james.arithmetic.question;

import java.io.Serializable;

// Represents an Arithmetic Question. This class is abstract because it only makes sense to
// create a specific type of ArithmeticQuestion such as AdditionQuestion or SubtractionQuestion.
public abstract class ArithmeticQuestion implements Serializable {

    private final double operand1;
    private final double operand2;

    public ArithmeticQuestion(double operand1, double operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    public double getOperand1() {
        return operand1;
    }
    public double getOperand2() {
        return operand2;
    }

    public abstract double getAnswer(); // this method must be defined by the subclass

    // Formats a number so that it does not have any unesscessary trailing zeros
    // e.g. "23.4500" will be converted to "23.45" and "23.0" will be converted to
    // "23"
    public static String removeTrailingZeros(String number) {

        int decimalPointIndex = number.indexOf(".");
        if (decimalPointIndex == -1) {
            // no decimal point found, so just return number as is
            return number;
        }

        // remove unecessary trailing zeros
        StringBuilder sb = new StringBuilder(number);
        while (true) {
            int lastIndex = sb.length()-1;
            if (sb.charAt(lastIndex) == '0') {
                sb.deleteCharAt(lastIndex);
            } else if (sb.charAt(lastIndex) == '.') {
                sb.deleteCharAt(lastIndex);
                break;
            } else {
                // encountered a non zero digit
                break;
            }
        }

        return sb.toString();
    }
}
