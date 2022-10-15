package company.vk.polis.ads.part4.denisstrizhkin.staircase;

import java.io.*;
import java.util.StringTokenizer;

// https://www.eolymp.com/ru/submissions/11802442
public class Main {
    private Main() {
        // Should not be instantiated
    }

    private static int[] arr;
    private static int[] dArr;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        if (n == 0) {
            out.println(0);
            return;
        }

        arr = new int[n];
        dArr = new int[n + 2];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        int k = in.nextInt();
        for (int i = 1; i < n + 1; i++) {
            dArr[i] = dArr[i - 1];
            for (int j = Math.max(0, i - k); j < i - 1; j++) {
                dArr[i] = Math.max(dArr[i], dArr[j]);
            }
            dArr[i] += arr[i - 1];
        }
        dArr[n + 1] = dArr[n];
        for (int j = Math.max(0, n + 1 - k); j < n; j++) {
            dArr[n + 1] = Math.max(dArr[n + 1], dArr[j]);
        }

        out.println(dArr[n + 1]);
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