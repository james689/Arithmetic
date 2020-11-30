package com.james.arithmetic.question;

import org.junit.Test;

public class QuestionFactoryTest {
    @Test
    public void testCreateAdditionQuestion() {
        AdditionQuestion additionQuestion = ArithmeticQuestionFactory.createAdditionQuestion(4,3,
                4,3);
        System.out.println("additionQuestion created");
        System.out.println("op1 = " + additionQuestion.getOperand1());
        System.out.println("op2 = " + additionQuestion.getOperand2());
        String op1 = Double.toString(additionQuestion.getOperand1());
        String op2 = Double.toString(additionQuestion.getOperand2());
        assert(op1.length() == 8);
        assert(op2.length() == 8);
    }

    @Test
    public void testCreateSubtractionQuestion() {
        SubtractionQuestion subtractionQuestion = ArithmeticQuestionFactory.createSubtractionQuestion(4,3,
                4,3);
        System.out.println("subtractionQuestion created");
        System.out.println("op1 = " + subtractionQuestion.getOperand1());
        System.out.println("op2 = " + subtractionQuestion.getOperand2());
        String op1 = Double.toString(subtractionQuestion.getOperand1());
        String op2 = Double.toString(subtractionQuestion.getOperand2());
        assert(op1.length() == 8);
        assert(op2.length() == 8);
        assert(subtractionQuestion.getOperand1() >= subtractionQuestion.getOperand2());
    }
}