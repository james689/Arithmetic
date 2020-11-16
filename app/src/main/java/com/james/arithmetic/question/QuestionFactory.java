package com.james.arithmetic.question;

import java.util.Random;

public class QuestionFactory {

    public static AdditionQuestion createAdditionQuestion(int numIntegerPartDigitsOp1, int numFractionalPartDigitsOp1,
                                                           int numIntegerPartDigitsOp2, int numFractionalPartDigitsOp2) {
        double op1 = randomNumber(numIntegerPartDigitsOp1, numFractionalPartDigitsOp1);
        double op2 = randomNumber(numIntegerPartDigitsOp2, numFractionalPartDigitsOp2);

        return new AdditionQuestion(op1, op2);
    }

    // digitsInOp1 must be >= digitsInOp2 otherwise we will be doing subtraction questions involving
    // negative number results
    public static SubtractionQuestion createSubtractionQuestion(int numIntegerPartDigitsOp1, int numFractionalPartDigitsOp1,
                                                                 int numIntegerPartDigitsOp2, int numFractionalPartDigitsOp2) {
        double op1 = randomNumber(numIntegerPartDigitsOp1, numFractionalPartDigitsOp1);
        double op2 = randomNumber(numIntegerPartDigitsOp2, numFractionalPartDigitsOp2);

        // find the largest of op1 and op2
        double largest = Math.max(op1, op2);
        double smallest = Math.min(op1, op2);

        return new SubtractionQuestion(largest, smallest);
    }

    public static MultiplicationQuestion createMultiplicationQuestion(int numIntegerPartDigitsOp1, int numFractionalPartDigitsOp1,
                                                                int numIntegerPartDigitsOp2, int numFractionalPartDigitsOp2) {
        double op1 = randomNumber(numIntegerPartDigitsOp1, numFractionalPartDigitsOp1);
        double op2 = randomNumber(numIntegerPartDigitsOp2, numFractionalPartDigitsOp2);

        return new MultiplicationQuestion(op1, op2);
    }

    public static DivisionQuestion createDivisionQuestion(int numIntegerPartDigitsOp1, int numFractionalPartDigitsOp1,
                                                                      int numIntegerPartDigitsOp2, int numFractionalPartDigitsOp2) {
        double op1 = randomNumber(numIntegerPartDigitsOp1, numFractionalPartDigitsOp1);
        double op2 = randomNumber(numIntegerPartDigitsOp2, numFractionalPartDigitsOp2);

        // make sure the divisor is smaller than the dividend
        double divisor = Math.min(op1, op2);
        double dividend = Math.max(op1, op2);

        return new DivisionQuestion(divisor, dividend);
    }

    // generate a random number with the specified number of digits for the
    // integer and fractional parts of the number
    // if numIntegerPartDigits = 4 and numFractionalPartDigits = 3, a number such as
    // 1456.234 will be generated.
    private static double randomNumber(int numIntegerPartDigits, int numFractionalPartDigits) {
        StringBuilder digits = new StringBuilder();
        Random rnd = new Random();
        // generate the integer part digits
        if (numIntegerPartDigits > 1) {
            // make sure the first integer part digit is not a 0, as otherwise you will
            // end up with a number with a leading zero like 03 or 04 and this leading zero
            // is meaningless.
            int randomNum = rnd.nextInt(10);
            while (randomNum == 0) {
                randomNum = rnd.nextInt(10);
            }
            digits.append(randomNum);
            numIntegerPartDigits--;
        }
        while (numIntegerPartDigits > 0) {
            digits.append(rnd.nextInt(10));
            numIntegerPartDigits--;
        }

        // generate the fractional part digits (if there is a fractional part)
        // make sure the last fractional part digit is not a 0, as otherwise you will
        // end up with a number with a trailing zero like 25.340 and this trailing zero
        // is meaningless
        if (numFractionalPartDigits > 0) {
            digits.append("."); // add the decimal point
            while (numFractionalPartDigits > 0) {
                int randomNum = rnd.nextInt(10);
                if (numFractionalPartDigits == 1 && randomNum == 0) {
                    // last fractional part digit cannot be 0
                    continue;
                }
                digits.append(randomNum);
                numFractionalPartDigits--;
            }
        }

        double result = Double.parseDouble(digits.toString());
        return result;
    }
}
