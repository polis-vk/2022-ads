package company.vk.polis.ads.workshop;

/* https://www.eolymp.com/ru/problems/3967
 */

import company.vk.polis.ads.SolveTemplate;

import java.io.*;
import java.util.StringTokenizer;

public class Ropes {
    private static void solve(final FastScanner in, final PrintWriter out) {
        // Write me
        int n = in.nextInt();
        int k = in.nextInt();
        int[] ropes = new int[n];
        int sum = 0;

        for (int i = 0; i < n; i++) {
            int rope = in.nextInt();
            ropes[i] = rope;
            sum += rope;
        }

        if (k > sum) {
            out.println(0);
            return;
        }

        int l = 1;
        int r = sum / k + 1;

        while (l < r - 1) {
            int mid = (l + r) >>> 1;
            int houseCount = 0;

            for (var length : ropes) {
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
