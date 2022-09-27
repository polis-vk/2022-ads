package company.vk.polis.ads;

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
    private Spread() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int N = in.nextInt();
        int[] array = new int[N];
        int min;
        int max;
        max = min = array[0];
        for (int i = 1; i < N; i++) {
            array[i] = in.nextInt();
            if (array[i] < min) {
                min = array[i];
            }
            if (array[i] > max) {
                max = array[i];
            }
        }
        int[] indexes = new int[max - min + 1];
        for (int number : array) {
            indexes[number - min]++;
        }
        // ?? int
        int calculatedIndex = 0;
        for (int i = 0; i < indexes.length; i++) {
            int temp = indexes[i];
            indexes[i] = calculatedIndex;
            calculatedIndex += temp;
        }

        int[] sortedArray = new int[N];

        for (int number : array) {
            sortedArray[indexes[number - min]] = number;
            indexes[number]++;
        }

        for (int number : sortedArray) {
            out.println(number);
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
