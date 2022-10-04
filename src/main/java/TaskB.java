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
public final class TaskB {
    private TaskB() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        System.out.println(mergeSequences(in.nextInt()));
    }

    private static long mergeSequences(long x) {
        long seqMemberA;
        long seqMemberB;
        long memberCx = 0;

        int a = 1;
        int b = 1;

        for (int currentX = 1; currentX <= x; currentX++) {
            seqMemberA = (long) a * a;
            seqMemberB = (long) b * b * b;
            if (seqMemberA == seqMemberB) {
                memberCx = seqMemberA;
                a++;
                b++;
            } else if (seqMemberB > seqMemberA) {
                memberCx = seqMemberA;
                a++;
            } else {
                memberCx = seqMemberB;
                b++;
            }
        }
        return memberCx;
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
