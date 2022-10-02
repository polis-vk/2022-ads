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
public final class Spread {
    private static final int maxDifference = 107;

    private Spread() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int N = in.nextInt();
        int[] array = new int[N];
        fillArray(array, in);
        countingSort(array);
        printArray(array, out);
    }

    private static void countingSort(int[] array) {
        int maxInArray = findMaxInArray(array);
        int minInArray = findMinInArray(array);
        int sizeCountingArray = maxInArray - minInArray + 1;
        int[] countingArray = new int[sizeCountingArray];
        for (int i = 0; i < array.length; i++) {
            countingArray[array[i] - minInArray]++;
        }
        for (int i = 1; i < sizeCountingArray; i++) {
            countingArray[i] += countingArray[i - 1];
        }
        int j = 0;
        for (int i = 0; i < sizeCountingArray; i++) {
            while (j < countingArray[i]) {
                array[j] = i + minInArray;
                j++;
            }
        }
    }

    private static void printArray(int[] array, final PrintWriter out) {
        for (int i = 0; i < array.length; i++) {
            out.print(array[i] + " ");
        }
    }

    private static int findMaxInArray(int[] array) {
        int max = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    private static int findMinInArray(int[] array) {
        int min = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    private static void fillArray(int[] array, final FastScanner in) {
        for (int i = 0; i < array.length; i++) {
            array[i] = in.nextInt();
        }
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