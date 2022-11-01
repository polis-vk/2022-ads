package company.vk.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Ropes {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] ropes = new int[n];
        long sum = 0;
        int rope;

        for (int i = 0; i < n; i++) {
            rope = in.nextInt();
            ropes[i] = rope;
            sum += rope;
        }
        if (sum < k) {
            System.out.println(0);
            return;
        }

        long l = 1;
        long r = sum / k + 1;
        while (l < r - 1) {
            long mid = (l + r) / 2;
            int houseCount = 0;
            long cnt;
            for (int length : ropes) {
                cnt = length / mid;
                houseCount += cnt;
            }
            if (houseCount < k) {
                r = mid;
            } else {
                l = mid;
            }
        }
        System.out.println(l);
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
