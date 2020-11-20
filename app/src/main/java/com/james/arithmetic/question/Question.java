package com.james.arithmetic.question;

import java.io.Serializable;

public abstract class Question implements Serializable {

    private final double operand1;
    private final double operand2;

    public Question(double operand1, double operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    public double getOperand1() {
        return operand1;
    }
    public double getOperand2() {
        return operand2;
    }

    public abstract double getAnswer();

    // helper method. lop's off trailing zero if required e.g. if the number is "8.0"
    // then this method will return "8".
    public static String getWholeNumberEquivalent(String number) {
        String fractionalPart = number.substring(number.indexOf(".")+1);
        if (fractionalPart.equals("0")) {
            number = number.substring(0, number.indexOf("."));
        }
        return number;
    }
}
