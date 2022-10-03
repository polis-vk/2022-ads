package company.vk.polis.ads.part2.paikeee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.TreeMap;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Scatter {

    private Scatter() {
        // Should not be instantiated
    }

    // Время работы: O(n)
    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        int n = Integer.parseInt(in.reader.readLine());
        TreeMap<Integer, Integer> valuesCount = new TreeMap<>();
        while (n > 0) {
            int index = in.nextInt();
            valuesCount.put(index, valuesCount.getOrDefault(index, 0) + 1);
            n--;
        }
        for (Integer value: valuesCount.keySet()) {
            int count = valuesCount.get(value);
            for (int i = 0; i < count; i++) {
                out.print(value + " ");
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
