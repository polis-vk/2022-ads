package company.vk.polis.ads.iampolshin;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Brackets {
    private static int[][] countTable;
    private static int[][] restoreTable;

    private Brackets() {
        // Should not be instantiated
    }

    //Решение: https://www.eolymp.com/ru/submissions/11786446
    private static void solve(final Scanner in, final PrintWriter out) {
        String brackets = in.nextLine();
        int length = brackets.length();
        countTable = new int[length][length];
        restoreTable = new int[length][length];
        for (int i = 0; i < length; i++) {
            countTable[i][i] = 1;
        }

        int currentCount;
        for (int j = 0; j < length; j++) {
            for (int i = j - 1; i >= 0; i--) {
                restoreTable[i][j] = i;
                currentCount = Integer.MAX_VALUE;
                if (isMatchingBrackets(brackets.charAt(i), brackets.charAt(j))) {
                    currentCount = countTable[i + 1][j - 1];
                }
                countTable[i][j] = getMinCount(i, j, currentCount);
            }
        }

        StringBuilder currentSequence = new StringBuilder();
        buildCorrectBracketSequence(brackets, 0, brackets.length() - 1, currentSequence);
        out.println(currentSequence.toString().trim());
    }

    private static int getMinCount(int fromInclusive, int toExclusive, int defaultCount) {
        int minCount = defaultCount;
        for (int k = fromInclusive; k < toExclusive; k++) {
            int temp = countTable[fromInclusive][k] + countTable[k + 1][toExclusive];
            if (temp < minCount) {
                minCount = temp;
                restoreTable[fromInclusive][toExclusive] = k;
            }
        }
        return minCount;
    }

    private static void buildCorrectBracketSequence(String brackets, int fromInclusive, int toExclusive,
                                                    StringBuilder currentSequence) {
        if (fromInclusive > toExclusive) {
            return;
        }

        if (toExclusive == fromInclusive) {
            char currentBracket = brackets.charAt(fromInclusive);
            if (isOpeningBracket(currentBracket)) {
                currentSequence.append(currentBracket)
                        .append(restoreMatchingBracket(currentBracket));
            } else {
                currentSequence.append(restoreMatchingBracket(currentBracket))
                        .append(currentBracket);
            }
            return;
        }

        if (isMatchingBrackets(brackets.charAt(fromInclusive), brackets.charAt(toExclusive))
                && countTable[fromInclusive][toExclusive] == countTable[fromInclusive + 1][toExclusive - 1]) {
            currentSequence.append(brackets.charAt(fromInclusive));
            buildCorrectBracketSequence(brackets, fromInclusive + 1, toExclusive - 1,
                    currentSequence);
            currentSequence.append(brackets.charAt(toExclusive));
            return;
        }

        buildCorrectBracketSequence(brackets, fromInclusive, restoreTable[fromInclusive][toExclusive], currentSequence);
        buildCorrectBracketSequence(brackets, restoreTable[fromInclusive][toExclusive] + 1, toExclusive,
                currentSequence);
    }

    private static boolean isMatchingBrackets(char openingBracket, char closingBracket) {
        return '(' == openingBracket && ')' == closingBracket || '[' == openingBracket && ']' == closingBracket;
    }

    private static char restoreMatchingBracket(char bracket) {
        switch (bracket) {
            case '(':
                return ')';
            case ')':
                return '(';
            case '[':
                return ']';
            case ']':
                return '[';
            default:
                return ' ';
        }
    }

    private static boolean isOpeningBracket(char bracket) {
        return "([".indexOf(bracket) != -1;
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}

