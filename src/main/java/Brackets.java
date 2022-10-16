import java.util.Arrays;
import java.util.Scanner;

public class Brackets {
    private static void getMinBrackets(String[][] data, int row, int column, int minLength) {
        for (int amountOfFirstElements = 0; amountOfFirstElements + row < column; amountOfFirstElements++) {
            final int currentLength = data[row][amountOfFirstElements + row].length() + data[amountOfFirstElements + row + 1][column].length();
            if (currentLength < minLength) {
                minLength = currentLength;
                data[row][column] = data[row][amountOfFirstElements + row] + data[amountOfFirstElements + row + 1][column];
            }
        }
    }

    private static boolean isPairedBrackets(String data, int row, int column) {
        return (data.charAt(row) == '(' && data.charAt(column) == ')') || (data.charAt(row) == '[' && data.charAt(column) == ']');
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String inputData = in.nextLine();
        final int size = inputData.length();
        if (size == 0) {
            System.out.println();
            return;
        }
        String[][] bracketsSequences = new String[size][size];
        for (int i = 0; i < size; i++) {
            Arrays.fill(bracketsSequences[i], "");
        }
        int row = 0;
        int column = 0;
        for (int i = 0; i < size; i++) {
            row = 0;
            column = i;
            while (row < size && column < size) {
                if (row == column) {
                    switch (inputData.charAt(row)) {
                        case '(', ')' -> bracketsSequences[row][column] = "()";
                        case '[', ']' -> bracketsSequences[row][column] = "[]";
                    }
                } else if (isPairedBrackets(inputData, row, column)) {
                    bracketsSequences[row][column] = inputData.charAt(row) + bracketsSequences[row + 1][column - 1] + inputData.charAt(column);
                    getMinBrackets(bracketsSequences, row, column, bracketsSequences[row][column].length());
                } else {
                    getMinBrackets(bracketsSequences, row, column, Integer.MAX_VALUE);
                }
                row++;
                column++;
            }
        }
        System.out.println(bracketsSequences[0][size - 1]);
    }
}