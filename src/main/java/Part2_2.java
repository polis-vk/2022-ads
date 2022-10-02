import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Part2_2 {
    private Part2_2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int cx = in.nextInt();
        long nextNumberPow2 = 1;
        long nextNumberPow3 = 1;
        long iPow2 = 1;
        long iPow3 = 1;
        int i = 0;
        long result = -1;
        while(i < cx) {
            if(nextNumberPow2 < nextNumberPow3) {
                result = nextNumberPow2;
                i++;

                iPow2++;
                nextNumberPow2 = iPow2 * iPow2;
            } else if (nextNumberPow2 == nextNumberPow3) {
                iPow2++;
                nextNumberPow2 = iPow2 * iPow2;
            } else {
                result = nextNumberPow3;
                i++;

                iPow3++;
                nextNumberPow3 = iPow3 * iPow3 * iPow3;
            }


        }

        out.println(result);
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