package com.james.arithmetic.question;

import org.junit.Test;

import static org.junit.Assert.*;

public class QuestionFactoryTest {
    @Test
    public void testCreateAdditionQuestion() {
        AdditionQuestion additionQuestion = QuestionFactory.createAdditionQuestion(5,3);
        System.out.println("additionQuestion created");
        System.out.println("op1 = " + additionQuestion.getOperand1());
        System.out.println("op2 = " + additionQuestion.getOperand2());
        String op1 = Integer.toString(additionQuestion.getOperand1());
        String op2 = Integer.toString(additionQuestion.getOperand2());
        assert(op1.length() == 5);
        assert(op2.length() == 3);
    }

    @Test
    public void testCreateSubtractionQuestion() {
        SubtractionQuestion subtractionQuestion = QuestionFactory.createSubtractionQuestion(5,5);
        System.out.println("subtractionQuestion created");
        System.out.println("op1 = " + subtractionQuestion.getOperand1());
        System.out.println("op2 = " + subtractionQuestion.getOperand2());
        String op1 = Integer.toString(subtractionQuestion.getOperand1());
        String op2 = Integer.toString(subtractionQuestion.getOperand2());
        assert(op1.length() == 5);
        assert(op2.length() == 5);
        assert(subtractionQuestion.getOperand1() >= subtractionQuestion.getOperand2());
    }
}