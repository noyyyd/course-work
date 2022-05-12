package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class View {
    public static final String PATH = "src/main/resources/result.txt";

    public void writeInFile(String input) {
        try (FileWriter fileWriter = new FileWriter(PATH, true)) {
            fileWriter.write(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String createTableString(char symbol, Stack<Character> stack,
                                  String polishNotation, int lengthExpression) {
        StringBuilder pieceTable = new StringBuilder();

        pieceTable.append(createEnterSymbolColumn(symbol));
        pieceTable.append(createStackColumn(stack, lengthExpression/2));
        pieceTable.append(createPolishNotationColumn(polishNotation, lengthExpression));
        pieceTable.append('\n');

        return pieceTable.toString();
    }

    public String createTableStringForCalculate(char symbol, Stack<Character> stack,
                                    int lengthExpression) {
        StringBuilder pieceTable = new StringBuilder();

        pieceTable.append(createEnterSymbolColumn(symbol));
        pieceTable.append(createStackColumnForCalculate(stack,
                lengthExpression));
        pieceTable.append('\n');

        return pieceTable.toString();
    }

    private String createStackColumn(Stack<Character> stack, int stackColumnWidth) {
        StringBuilder stackColumn = new StringBuilder();
        stackColumn.append(" |");
        while (stack.peek() != '#'){
            stackColumn.append(stack.pop());
        }
        while (stackColumn.toString().length() < stackColumnWidth) {
            stackColumn.append(' ');
        }
        stackColumn.append("| ");

        return stackColumn.toString();
    }

    private String createStackColumnForCalculate(Stack<Character> stack, int stackColumnWidth) {
        StringBuilder stackColumn = new StringBuilder();
        stackColumn.append(" |");
        while (!stack.empty()){
            stackColumn.append(' ');
            stackColumn.append(stack.pop());
        }
        while (stackColumn.toString().length() < stackColumnWidth*2) {
            stackColumn.append(' ');
        }
        stackColumn.append("| ");

        return stackColumn.toString();
    }

    private String createEnterSymbolColumn(char symbol) {
        StringBuilder enterSymbolColumn = new StringBuilder();
        enterSymbolColumn.append("| ");
        enterSymbolColumn.append(symbol);
        enterSymbolColumn.append(" |");

        return enterSymbolColumn.toString();
    }

    private String createPolishNotationColumn (String polishNotation,
                                               int lengthExpression) {
        StringBuilder polishNotationColumn = new StringBuilder();
        polishNotationColumn.append("| ");
        polishNotationColumn.append(polishNotation);
        while (polishNotationColumn.toString().length() < lengthExpression) {
            polishNotationColumn.append(' ');
        }
        polishNotationColumn.append(" |");

        return polishNotationColumn.toString();
    }


}
