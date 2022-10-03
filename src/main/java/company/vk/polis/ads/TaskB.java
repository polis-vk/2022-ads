package company.vk.polis.ads;

import java.io.*;
import java.util.StringTokenizer;

public class TaskB {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int x = in.nextInt();
        long i = 0, j = 0;

        long elem = 0;
        int k = 0;
        while (k <= x) {
            if (i * i < j * j * j) {
                elem = i * i;
                i++;
            } else if (i * i == j * j * j) {
                elem = i * i;
                i++;
                j++;
            } else {
                elem = j * j * j;
                j++;
            }
            k++;
        }
        System.out.println(elem);
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
