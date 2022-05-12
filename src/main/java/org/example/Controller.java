package org.example;

import java.util.Scanner;

public class Controller {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Model model = new Model();
        View view = new View();
        String expression = s.next();
        view.writeInFile(expression + '\n');
        String polishNotation = model.parseExpression(expression);
        view.writeInFile(polishNotation + '\n');
        view.writeInFile("Result: " + model.calculatePolishNotation(polishNotation));
    }
}
