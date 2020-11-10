package com.james.arithmetic.question;

public class SubtractionQuestion extends Question {

    public SubtractionQuestion(double op1, double op2) {
        super(op1,op2);
    }

    public double getAnswer() {
        return getOperand1() - getOperand2();
    }

    @Override
    public String toString() {
        return getOperand1() + "-" + getOperand2();
    }
}
