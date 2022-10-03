package company.vk.polis.ads;

import java.io.*;
import java.util.StringTokenizer;

public class Task_B {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int x = in.nextInt();
        long i = 1, j = 1;
        long result = 0;
        int index = 0;
        while (x != index) {
            long a = i * i;
            long b = j * j * j;
            if (a == b) {
                result = a;
                i++;
                j++;
            } else if (a < b) {
                result = a;
                i++;
            } else {
                result = b;
                j++;
            }
            index++;
        }
        System.out.println(result);
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
