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
public final class task2_1425 {
    private task2_1425() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        // Write me
        int cntOfElements = in.nextInt();
        int[] arr = new int[cntOfElements];
        for (int i = 0; i < cntOfElements; i++) {
            arr[i] = in.nextInt();
        }
        sort(arr);
        for (int i = 0; i < cntOfElements; i++) {
            out.print(arr[i] + " ");
        }
    }

    private static void sort(int[] arr) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int c : arr) {
            min = (min > c ? c : min);
            max = (max < c ? c : max);
        }

        int[] cnt = new int[max - min + 1];

        for (int c : arr) {
            cnt[c - min]++;
        }

        int pointer = 0;

        for (int i = 0; i < cnt.length; i++) {
            for (int j = 0; j < cnt[i]; j++) {
                arr[pointer++] = i + min;
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
