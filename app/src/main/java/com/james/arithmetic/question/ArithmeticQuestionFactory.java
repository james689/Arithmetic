package com.james.arithmetic.question;

import java.util.Random;

// Helper class for creating the different types of Arithmetic Question
public class ArithmeticQuestionFactory {

    public static AdditionQuestion createAdditionQuestion(int numIntDigitsOp1, int numFractionalDigitsOp1,
                                                           int numIntDigitsOp2, int numFractionalDigitsOp2) {
        double op1 = randomNumber(numIntDigitsOp1, numFractionalDigitsOp1);
        double op2 = randomNumber(numIntDigitsOp2, numFractionalDigitsOp2);

        return new AdditionQuestion(op1, op2);
    }

    public static SubtractionQuestion createSubtractionQuestion(int numIntDigitsMinuend, int numFractionalDigitsMinuend,
                                                                 int numIntDigitsSubtrahend, int numFractionalDigitsSubtrahend) {
        double minuend = randomNumber(numIntDigitsMinuend, numFractionalDigitsMinuend);
        double subtrahend = randomNumber(numIntDigitsSubtrahend, numFractionalDigitsSubtrahend);

        return new SubtractionQuestion(minuend, subtrahend);
    }

    public static MultiplicationQuestion createMultiplicationQuestion(int numIntDigitsOp1, int numFractionalDigitsOp1,
                                                                int numIntDigitsOp2, int numFractionalDigitsOp2) {
        double op1 = randomNumber(numIntDigitsOp1, numFractionalDigitsOp1);
        double op2 = randomNumber(numIntDigitsOp2, numFractionalDigitsOp2);

        return new MultiplicationQuestion(op1, op2);
    }

    public static DivisionQuestion createDivisionQuestion(int numIntDigitsDividend, int numFractionalDigitsDividend,
                                                                      int numIntDigitsDivisor, int numFractionalDigitsDivisor) {
        double dividend = randomNumber(numIntDigitsDividend, numFractionalDigitsDividend);
        double divisor = randomNumber(numIntDigitsDivisor, numFractionalDigitsDivisor);

        return new DivisionQuestion(dividend, divisor);
    }

    // generate a random number with the specified number of digits for the integer and fractional
    // parts of the number e.g. if numIntegerPartDigits = 4 and numFractionalPartDigits = 3, a number
    // such as 1456.234 will be generated.
    // if numIntegerPartDigits == 1, then a 0 will not be generated for the digit, since what is the point
    // in a problem such as 369 + 0 or even worst 234 / 0
    private static double randomNumber(int numIntegerPartDigits, int numFractionalPartDigits) {
        StringBuilder digits = new StringBuilder();
        Random rnd = new Random();

        // generate the integer part digits
        // the first digit should never be zero, as otherwise you will end up with a number with a
        // leading zero like 03 or 04 in a multi-digit number and this leading zero is meaningless.
        // Or if it is a single-digit number you will end up with 0 for your number and you don't want
        // any numbers in your arithmetic problems to be zero e.g. 369 + 0 or even worst 234 / 0
        int randomNum = rnd.nextInt(10);
        while (randomNum == 0) {
            randomNum = rnd.nextInt(10);
        }
        digits.append(randomNum);
        numIntegerPartDigits--;

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
                randomNum = rnd.nextInt(10);
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
