//package company.vk.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Palindrome {
    private Palindrome() {
        // Should not be instantiated
    }

    private static StringBuilder selectEquals(char[] array, int fromInclusive) {
        int lastIndex = fromInclusive;
        while (lastIndex < array.length - 1 && array[lastIndex] == array[lastIndex + 1]) {
            lastIndex++;
        }
        StringBuilder equalsBuilder = new StringBuilder();
        for (int i = 0; i < lastIndex - fromInclusive + 1; i++) {
            equalsBuilder.append(array[fromInclusive + i]);
        }
        return equalsBuilder;
    }

    public static void countingSort(char[] array) {
        char min = array[0];
        char max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
            if (array[i] > max) {
                max = array[i];
            }
        }

        int[] indexes = new int[max - min + 1];

        for (char element : array) {
            indexes[element - min]++;
        }

        int calculatedIndex = 0;
        for (int i = 0; i < indexes.length; i++) {
            int temp = indexes[i];
            indexes[i] = calculatedIndex;
            calculatedIndex += temp;
        }

        char[] newArray = new char[array.length];
        for (char element : array) {
            newArray[indexes[element - min]] = element;
            indexes[element - min]++;
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = newArray[i];
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int N = in.nextInt();
        char[] letters = in.next().toCharArray();
        // O(N)
        countingSort(letters);
        int i = 0;
        char minOddLetter = 'Z' + 1;
        StringBuilder evenPalindromeBuilder = new StringBuilder();
        // Тоже O(N), т. к. каждый раз к i прибавляется количество одинаковых значений,
        // которые были найдены в функции
        while (i < letters.length) {
            StringBuilder equals = selectEquals(letters, i);
            if (equals.length() % 2 != 0 && letters[i] < minOddLetter) {
                minOddLetter = letters[i];
            }
            evenPalindromeBuilder.append(equals.substring(0, equals.length() / 2));
            i += equals.length();
        }
        out.println(
            evenPalindromeBuilder.toString() +
            (minOddLetter < 'Z' + 1 ? minOddLetter : "") +
            evenPalindromeBuilder.reverse().toString()
        );
    }

    private static final class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
