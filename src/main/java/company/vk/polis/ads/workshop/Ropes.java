package company.vk.polis.ads.workshop;



import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * https://www.eolymp.com/ru/submissions/12002108
 *
 * @author Dmitry Schitinin
 */

public class Ropes {
    private Ropes() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] ropes = new int[n];
        int sum = 0;
        for(int i = 0; i < n; i++) {
            int rope = in.nextInt();
            ropes[i] = rope;
            sum += rope;
        }

        int l = 0;
        int r = 10000001;
        while(l < r - 1) {
            int mid = (l + r) >>> 1;
            int houseCount = 0;
            for(var length : ropes) {
                int cnt = length / mid;
                houseCount += cnt;
            }
            if (houseCount < k) {
                r = mid;

            } else {
                l = mid;
            }
        }
        out.println(l);
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
