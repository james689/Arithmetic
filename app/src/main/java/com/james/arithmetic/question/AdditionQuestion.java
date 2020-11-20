package com.james.arithmetic.question;

public class AdditionQuestion extends Question {

    public AdditionQuestion(double op1, double op2) {
        super(op1,op2);
    }

    public double getAnswer() {
        return getOperand1() + getOperand2();
    }

    @Override
    public String toString() {

        String operand1 = Double.toString(getOperand1());
        String operand2 = Double.toString(getOperand2());

        return Question.getWholeNumberEquivalent(operand1) + "+" + Question.getWholeNumberEquivalent(operand2);
    }
}
