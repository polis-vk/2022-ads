package company.vk.polis.ads;

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
public final class MergeSubsequence {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int capacity = in.nextInt();
        long indexForA = 1;
        long indexForB = 1;
        long result = 0;
        for (long i = 1; i <= capacity; i++) {
            if (indexForA * indexForA == indexForB * indexForB * indexForB) {
                result = indexForA * indexForA;
                indexForA++;
                indexForB++;
            } else if (indexForA * indexForA < indexForB * indexForB * indexForB) {
                result = indexForA * indexForA;
                indexForA++;
            } else {
                result = indexForB * indexForB * indexForB;
                indexForB++;
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

