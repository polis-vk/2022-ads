package company.vk.polis.ads;

import java.io.*;
import java.util.StringTokenizer;

public class LastDigit {
    private static void solve(final LastDigit.FastScanner in, final PrintWriter out) {
        try {
            int n = in.nextInt();
            out.println(n % 10);
        } catch (NumberFormatException ignore) {
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

        int nextInt() throws NumberFormatException {
            return Integer.parseInt(next());
        }
    }

    public static void main(final String[] arg) {
        final LastDigit.FastScanner in = new LastDigit.FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
