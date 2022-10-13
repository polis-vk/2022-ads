package company.vk.polis.ads;

import java.util.Scanner;

public class BracketsSequence {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String brackets = in.nextLine();
        if (brackets.length() == 0) {
            System.out.println("");
            return;
        }
        final int n = brackets.length();
        String[][] bestSequences = new String[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    switch (brackets.charAt(i)) {
                        case '(':
                        case ')':
                            bestSequences[i][j] = "()";
                            break;
                        case '[':
                        case ']':
                            bestSequences[i][j] = "[]";
                            break;
                    }
                } else {
                    bestSequences[i][j] = "";
                }
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                if (
                    (brackets.charAt(j) == '(' && brackets.charAt(j + i) == ')') ||
                    (brackets.charAt(j) == '[' && brackets.charAt(j + i) == ']')
                ) {
                    bestSequences[j][j + i] = brackets.charAt(j) +
                            bestSequences[j + 1][j + i - 1] +
                            brackets.charAt(j + i);
                } else {
                    bestSequences[j][j + i] = bestSequences[j][j] + bestSequences[j + 1][j + i];
                }
                for (int k = j; k < j + i; k++) {
                    String sequence = bestSequences[j][k] + bestSequences[k + 1][j + i];
                    if (sequence.length() < bestSequences[j][j + i].length()) {
                        bestSequences[j][j + i] = sequence;
                    }
                }
            }
        }
        System.out.println(bestSequences[0][n - 1]);
    }
}
