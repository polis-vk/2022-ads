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
public final class task2_1442 {
    private task2_1442() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        out.println(getC(in.nextInt()));

    }

    private static long getC(int i) {
        int aCnt = 0;
        int bCnt = 0;
        int iCnt = 0;
        long aVal;
        long bVal;
        while (iCnt < i) {
            aVal = getA(aCnt);
            bVal = getB(bCnt);
            if (aVal <= bVal) {
                aCnt++;
            }
            if (aVal >= bVal) {
                bCnt++;
            }
            iCnt++;
        }
        aVal = getA(aCnt);
        bVal = getB(bCnt);
        return Math.min(aVal, bVal);
    }

    private static long getA(long i) {
        return i * i;
    }

    private static long getB(long i) {
        return i * i * i;
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
