package com.calculator.core;

/**
 * Handles all calculation logic for the scientific calculator.
 */
public class CalculatorEngine {

    /**
     * Performs a binary operation on two numbers.
     *
     * @param first   the first number
     * @param second  the second number
     * @param operator the operation to perform
     * @return the result of the operation
     * @throws ArithmeticException if the operation is invalid (e.g., division by zero)
     */
    public double performBinaryOperation(double first, double second, Operations operator) throws ArithmeticException {
        switch (operator) {
            case ADD:
                return first + second;
            case SUBTRACT:
                return first - second;
            case MULTIPLY:
                return first * second;
            case DIVIDE:
                if (second == 0) throw new ArithmeticException("Division by zero");
                return first / second;
            case MODULUS:
                if (second == 0) throw new ArithmeticException("Modulus by zero");
                return first % second;
            case POWER:
                return Math.pow(first, second);
            default:
                throw new IllegalArgumentException("Unsupported operation: " + operator);
        }
    }

    /**
     * Performs a unary operation on a single number.
     *
     * @param number   the input number
     * @param operator the operation to perform
     * @return the result of the operation
     * @throws ArithmeticException if the operation is invalid
     */
    public double performUnaryOperation(double number, Operations operator) throws ArithmeticException {
        switch (operator) {
            case SIN:
                return Math.sin(Math.toRadians(number));
            case COS:
                return Math.cos(Math.toRadians(number));
            case TAN:
                return Math.tan(Math.toRadians(number));
            case LOG:
                if (number <= 0) throw new ArithmeticException("Logarithm of non-positive number");
                return Math.log10(number);
            case LN:
                if (number <= 0) throw new ArithmeticException("Natural logarithm of non-positive number");
                return Math.log(number);
            case EXP:
                return Math.exp(number);
            case SQUARE:
                return number * number;
            case RECIPROCAL:
                if (number == 0) throw new ArithmeticException("Reciprocal of zero");
                return 1 / number;
            case SQRT:
                if (number < 0) throw new ArithmeticException("Square root of negative number");
                return Math.sqrt(number);
            case NEGATE:
                return -number;
            case FACTORIAL:
                if (number < 0 || number != Math.floor(number))
                    throw new ArithmeticException("Factorial of negative or non-integer");
                return calculateFactorial((int) number);
            case PI:
                return Math.PI;
            case TEN_POWER:
                return Math.pow(10, number);
            case ABSOLUTE:
                return Math.abs(number);
            default:
                throw new IllegalArgumentException("Unsupported operation: " + operator);
        }
    }

    /**
     * Calculates the factorial of a number.
     *
     * @param n the input number
     * @return the factorial
     */
    private double calculateFactorial(int n) {
        if (n == 0 || n == 1) return 1;
        double result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * Formats the result to remove unnecessary decimal places.
     *
     * @param result the number to format
     * @return formatted string
     */
    public String formatResult(double result) {
        if (result == (long) result) {
            return String.valueOf((long) result);
        } else {
            return String.format("%.6f", result).replaceAll("\\.?0*$", "");
        }
    }
}