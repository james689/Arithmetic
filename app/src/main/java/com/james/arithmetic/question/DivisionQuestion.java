package com.james.arithmetic.question;

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
        String dividend = Double.toString(getDividend());
        String divisor = Double.toString(getDivisor());

        return ArithmeticQuestion.getWholeNumberEquivalent(dividend) + "/" + ArithmeticQuestion.getWholeNumberEquivalent(divisor);
    }
}
