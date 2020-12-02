package com.james.arithmetic.question;

import java.text.DecimalFormat;

public class DivisionQuestion extends ArithmeticQuestion {

    public DivisionQuestion(double dividend, double divisor) {
        super(dividend, divisor);
    }

    public double getDividend() {
        return getOperand1();
    }
    public double getDivisor() {
        return getOperand2();
    }

    public double getAnswer() {
        return getDividend() / getDivisor();
    }

    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("0.0000000000");

        String dividend = decimalFormat.format(getDividend());
        String divisor = decimalFormat.format(getDivisor());

        return ArithmeticQuestion.removeTrailingZeros(dividend) + "/" + ArithmeticQuestion.removeTrailingZeros(divisor);
    }
}
