package com.james.arithmetic.question;

public class DivisionQuestion extends Question {

    public DivisionQuestion(double divisor, double dividend) {
        super(divisor, dividend);
    }

    public double getDivisor() {
        return getOperand1();
    }

    public double getDividend() {
        return getOperand2();
    }

    public double getAnswer() {
        return getDividend() / getDivisor();
    }

    @Override
    public String toString() {
        String dividend = Double.toString(getDividend());
        String divisor = Double.toString(getDivisor());

        return Question.getWholeNumberEquivalent(dividend) + "/" + Question.getWholeNumberEquivalent(divisor);
    }
}
