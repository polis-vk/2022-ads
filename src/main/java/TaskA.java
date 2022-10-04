import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TaskA {
    private TaskA() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] buffer = new int[n];

        for (int i = 0; i < n; i++) {
            buffer[i] = in.nextInt();
        }

        int min = minimum(buffer);
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] -= min;
        }
        sortLSD(buffer);

        for (int element : buffer) {
            out.print(element + min + " ");
        }
    }

    private static void sortLSD(int[] buffer) {
        List<Integer>[] buckets = new ArrayList[10];
        int digit;

        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<Integer>();
        }

        for (int degree = 0; degree < 3; degree++) {
            for (int i : buffer) {
                digit = (int) ((i / Math.pow(10, degree)) % 10);
                buckets[digit].add(i);
            }
            int k = 0;
            for (int i = 0; i < 10; i++) {
                for (Integer j : buckets[i]) {
                    buffer[k++] = j;
                }
                buckets[i].clear();
            }
        }
    }

    private static int minimum(int[] buffer) {
        int min = Integer.MAX_VALUE;
        for (int i : buffer) {
            if (i < min) {
                min = i;
            }
        }
        return min;
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
