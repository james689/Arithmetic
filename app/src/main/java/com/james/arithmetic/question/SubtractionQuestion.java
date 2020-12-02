package com.james.arithmetic.question;

import java.text.DecimalFormat;

public class SubtractionQuestion extends ArithmeticQuestion {

    public SubtractionQuestion(double minuend, double subtrahend) {
        super(minuend,subtrahend);
    }

    public double getMinuend() {
        return getOperand1();
    }
    public double getSubtrahend() {
        return getOperand2();
    }

    public double getAnswer() {
        return getMinuend() - getSubtrahend();
    }

    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("0.0000000000");

        String minuend = decimalFormat.format(getMinuend());
        String subtrahend = decimalFormat.format(getSubtrahend());

        return ArithmeticQuestion.removeTrailingZeros(minuend) + "-" + ArithmeticQuestion.removeTrailingZeros(subtrahend);
    }
}
