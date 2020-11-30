package com.james.arithmetic.question;

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
        String minuend = Double.toString(getMinuend());
        String subtrahend = Double.toString(getSubtrahend());

        return ArithmeticQuestion.getWholeNumberEquivalent(minuend) + "-" + ArithmeticQuestion.getWholeNumberEquivalent(subtrahend);
    }
}
