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
public final class TaskA {
    private TaskA() {
        // Should not be instantiated
    }

    private static int min(int[] a) {
        int min = a[0];
        for (int j : a) {
            if (j < min) {
                min = j;
            }
        }
        return min;
    }

    private static void countSort(int[] a, int k) {
        int[] counter = new int[k];
        int min = min(a);
        int currentNumber;
        int currentCount;

        for (int value : a) {
            counter[value - min] += 1;
        }

        currentNumber = min;

        for (int i = 0, j = 0; i < k; i++, currentNumber++) {
            currentCount = counter[i];
            while (currentCount > 0) {
                a[j] = currentNumber;
                j++;
                currentCount--;
            }
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int k = 107;

        int n = in.nextInt();
        int[] a = new int[n];

        for (int i = 0; i < a.length; i++) {
            a[i] = in.nextInt();
        }

        countSort(a, k);

        for (int i : a) {
            out.print(i + " ");
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
