package company.vk.polis.ads.workshop;

import java.io.*;
import java.util.StringTokenizer;

public class Ropes {

    //https://www.eolymp.com/ru/submissions/11984783

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] ropes = new int[n];
        ropes[0] = in.nextInt();
        int max = ropes[0];
        for (int i = 1; i < n; i++) {
            ropes[i] = in.nextInt();
            if (ropes[i] > max) {
                max = ropes[i];
            }
        }

        int l = 0;
        int r = max + 1;

        while (l < r - 1) {
            int mid = (l + r) >>> 1;
            int houseCount = 0;
            for (var length : ropes) {
                houseCount += length / mid;
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
