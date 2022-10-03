import java.io.*;
import java.util.StringTokenizer;

public final class Task2 {
    private Task2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int i = 1;
        int j = 1;
        long square = 1;
        long cube = 1;
        long ans = 0;
        for (int x = in.nextInt(); x > 0; x--) {
            if (square == cube) {
                ans = square;
                square = (long) Math.pow(++i, 2);
                cube = (long) Math.pow(++j, 3);
            } else if (square < cube) {
                ans = square;
                square = (long) Math.pow(++i, 2);
            } else {
                ans = cube;
                cube = (long) Math.pow(++j, 3);
            }
        }
        out.println(ans);
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
