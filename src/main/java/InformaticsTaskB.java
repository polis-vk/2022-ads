import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Math.pow;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class InformaticsTaskB {
    private InformaticsTaskB() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int position = in.nextInt();
        long currElementA = 1;
        long currElementB = 1;
        int indexA = 1;
        int indexB = 1;
        long result = 0;
        for (int i = 0; i < position; i++) {
            if (currElementA == currElementB) {
                result = currElementA;
                currElementA = (long) pow(++indexA, 2);
                currElementB = (long) pow(++indexB, 3);
            } else if (currElementA > currElementB) {
                result = currElementB;
                currElementB = (long) pow(++indexB, 3);
            } else {
                result = currElementA;
                currElementA = (long) pow(++indexA, 2);
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

