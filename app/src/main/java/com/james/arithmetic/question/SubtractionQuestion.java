package com.james.arithmetic.question;

public class SubtractionQuestion extends Question {

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

        return Question.getWholeNumberEquivalent(minuend) + "-" + Question.getWholeNumberEquivalent(subtrahend);
    }
}
