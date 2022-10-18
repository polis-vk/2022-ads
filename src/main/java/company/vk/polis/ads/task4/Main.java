package company.vk.polis.ads.task4;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 * https://www.eolymp.com/ru/submissions/11840942
 * @author Dmitry Schitinin
 */
public final class Main {
    private Main() {
        // Should not be instantiated
    }
    private static int maxFromPrev(int[] stairsCost, int i, int k) {
        int result = Integer.MIN_VALUE;
        for(int j = i - 1; j >= i - k && j >= 0; j--) {
            if(stairsCost[j] > result) {
                result = stairsCost[j];
            }
        }
        return result;
    }
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] stairsCost = new int[n + 2];
        stairsCost[0] = 0;
        stairsCost[n + 1] = 0;
        for(int i = 1; i < n + 1; i++) {
            stairsCost[i] = in.nextInt();
        }
        int k = in.nextInt();
        for(int i = 1; i <= n + 1; i++) {
            stairsCost[i] += maxFromPrev(stairsCost, i, k);
        }
        out.println(stairsCost[n + 1]);
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
