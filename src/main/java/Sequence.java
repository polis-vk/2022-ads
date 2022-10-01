import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Sequence {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int range = in.nextInt();
        int countA = 1;
        int countB = 1;
        long cX = 0;
        for (int i = 1; i <= range; i++) {
            long powA = (long) countA * countA;
            long powB = (long) countB * countB * countB;
            if (powA == powB) {
                cX = powA;
                countA++;
                countB++;
            } else if (powA < powB) {
                cX = powA;
                countA++;
            } else {
                cX = powB;
                countB++;
            }
        }

        out.print(cX);
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
