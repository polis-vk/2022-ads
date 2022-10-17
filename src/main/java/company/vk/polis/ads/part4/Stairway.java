package company.vk.polis.ads.part4;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 *
 * https://www.eolymp.com/ru/submissions/11803091
 */
public final class Stairway {
    private Stairway() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int count = in.nextInt();
        long[] arr = new long[count + 2];
        for (int i = 1; i < count + 1; i++) {
            arr[i] = in.nextInt();
        }
        int step = in.nextInt();
        for (int i = 1; i <= count + 1; i++) {
            long maxValue = Long.MIN_VALUE;
            int startIndex = Math.max(i - step, 0);
            for (int j = startIndex; j < i; j++) {
                if (arr[j] > maxValue) {
                    maxValue = arr[j];
                }
            }
            arr[i] += maxValue;
        }
        out.println(arr[count + 1]);
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
