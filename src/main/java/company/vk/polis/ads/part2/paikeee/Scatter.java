package company.vk.polis.ads.part2.paikeee;

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
public final class Scatter {

    private static final byte CAPACITY = 107;

    private Scatter() {
        // Should not be instantiated
    }

    // Время работы: O(n)
    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        int n = Integer.parseInt(in.reader.readLine());
        int[] elements = new int[n];
        int minElement = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            elements[i] = in.nextInt();
            if (elements[i] < minElement) {
                minElement = elements[i];
            }
        }
        int[] result = new int[CAPACITY];
        for (int i = 0; i < n; i++) {
           result[elements[i] - minElement]++;
        }
        for (int i = 0; i < CAPACITY; i++) {
            if (result[i] > 0) {
                for (int j = 0; j < result[i]; j++) {
                    out.print(i + minElement + " ");
                }
            }
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

    public static void main(final String[] arg) throws IOException {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
