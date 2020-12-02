package com.james.arithmetic.question;

import java.text.DecimalFormat;

public class MultiplicationQuestion extends ArithmeticQuestion {

    public MultiplicationQuestion(double op1, double op2) {
        super(op1,op2);
    }

    public double getAnswer() {
        return getOperand1() * getOperand2();
    }

    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("0.0000000000");

        String operand1 = decimalFormat.format(getOperand1());
        String operand2 = decimalFormat.format(getOperand2());

        return ArithmeticQuestion.removeTrailingZeros(operand1) + "x" + ArithmeticQuestion.removeTrailingZeros(operand2);
    }
}
