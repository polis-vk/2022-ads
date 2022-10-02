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
public final class B {
    private B() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        // Индекс, на котором нужно остановиться
        long x = in.nextInt();

        // Индексы последовательностей
        long i = 1;
        long j = 1;
        long k = 0;

        // Значения элементов последовательностей
        long A_i = 0;
        long B_j = 0;

        for (; k < x; k++) {
            A_i = i * i;
            B_j = j * j * j;
            if (A_i < B_j) {
                i++;
            } else if (B_j < A_i) {
                j++;
            } else {
                i++;
                j++;
            }
        }
        out.println(Long.min(A_i, B_j));
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
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
}
