import java.io.*;
import java.util.StringTokenizer;

public class LastDigit {
    private LastDigit() {
        // Should not be instantiated
    }

    private static void solve(final LastDigit.FastScanner in, final PrintWriter out) {
        int inputNumber = in.nextInt();
        int lastDigit = inputNumber % 10;
        out.println(lastDigit);
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
        final LastDigit.FastScanner in = new LastDigit.FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
