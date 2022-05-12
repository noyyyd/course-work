package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Model {
    Map<Character, Integer> operandPriority;

    public Model() {
        operandPriority = new HashMap<>();
        operandPriority.put('(', 0);
        operandPriority.put(')', 0);
        operandPriority.put('+', 1);
        operandPriority.put('-', 1);
        operandPriority.put('*', 2);
        operandPriority.put('/', 2);
        operandPriority.put('#', -1);
    }

    public String parseExpression(String expression) {
        StringBuilder polishNotation = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        stack.push('#');
        char[] symbols = expression.toCharArray();

        for (char symbol : symbols) {
            if (isOperand(symbol)) {
                polishNotation.append(symbol);
                enterValuesForParse(symbol, (Stack<Character>) stack.clone(), polishNotation.toString(),
                        expression.length());
                continue;
            }

            if (symbol == '(') {
                stack.push(symbol);
                enterValuesForParse(symbol, (Stack<Character>) stack.clone(), polishNotation.toString(),
                        expression.length());
                continue;
            }

            if (symbol == ')') {
                while (stack.peek() != '(') {
                    polishNotation.append(stack.pop());
                }
                stack.pop();
                enterValuesForParse(symbol, (Stack<Character>) stack.clone(), polishNotation.toString(),
                        expression.length());
                continue;
            }

            if (operandPriority.get(symbol) > operandPriority.get(stack.peek())) {
                stack.push(symbol);
            } else {
                while (operandPriority.get(symbol) <= operandPriority.get(stack.peek())) {
                    polishNotation.append(stack.pop());
                }
                stack.push(symbol);
            }
            enterValuesForParse(symbol, (Stack<Character>) stack.clone(), polishNotation.toString(),
                    expression.length());
        }

        while (stack.peek() != '#') {
            polishNotation.append(stack.pop());
        }

        return polishNotation.toString();
    }

    public String calculatePolishNotation(String polishNotation) {
        Scanner s = new Scanner(System.in);
        char[] symbols = polishNotation.toCharArray();
        Stack<Integer> stack = new Stack<>();

        for (char symbol : symbols) {
            if (isOperand(symbol)) {
                System.out.println("Введите занчеие " + symbol + ": ");
                stack.push(s.nextInt());
            } else {
                stack.push(calculateIntermediateValue(stack, symbol));
            }
            enterValuesForCalculate(symbol, (Stack<Character>) stack.clone(), polishNotation.length());
        }

        return String.valueOf(stack.pop());
    }

    private int calculateIntermediateValue(Stack stack, char operator) {
        int secondValue = (int) stack.pop();
        int firstValue = (int) stack.pop();
        int result;
        switch (operator) {
            case ('+'):
                result = firstValue + secondValue;
                break;
            case ('-'):
                result = firstValue - secondValue;
                break;
            case ('*'):
                result = firstValue * secondValue;
                break;
            default:
                result = firstValue / secondValue;
                break;
        }

        return result;
    }

    private boolean isOperand(char symbol) {
        char[] operators = {'+', '-', '*', '/', '(', ')'};

        for (char operator : operators) {
            if (symbol == operator) {
                return false;
            }
        }
        return true;
    }

    private void enterValuesForParse(char symbol, Stack<Character> stack,
                                     String polishNotation, int lengthExpression) {
        View view = new View();
        view.writeInFile(view.createTableString(symbol, stack,
                polishNotation, lengthExpression));
    }

    private void enterValuesForCalculate(char symbol, Stack<Character> stack,
                                         int lengthExpression) {
        View view = new View();
        view.writeInFile(view.createTableStringForCalculate(symbol, stack,
                lengthExpression));
    }
}
