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
}
