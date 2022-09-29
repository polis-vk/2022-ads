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
        int indexJustPrinted = 0;
        int capacity = in.nextInt();
        long indexStable = 0;
        long indexRunning = 2;
        long result = 0;
        do {
            indexStable++;
            if (capacity == 1) {
                out.println(capacity);
                break;
            }
            if (indexStable == 1) {
                indexJustPrinted = 1;
                continue;
            }
            while (indexRunning * indexRunning < indexStable * indexStable * indexStable) {
                result = indexRunning * indexRunning;
                indexJustPrinted++;
                indexRunning++;
                if (indexJustPrinted == capacity) {
                    break;
                }
            }
            if (indexJustPrinted == capacity) { // сделать только одну проверку
                break;
            }
            if (indexRunning * indexRunning == indexStable * indexStable * indexStable) {
                indexRunning++;
            } //
            result = indexStable *indexStable*indexStable;
            indexJustPrinted++;

        } while (indexJustPrinted != capacity);
        if (capacity != 1) {
            out.println(result);
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

