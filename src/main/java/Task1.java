import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.EnumSet;
import java.util.StringTokenizer;

import javax.swing.plaf.IconUIResource;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Task1 {
    private Task1() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int count = in.nextInt();

        if (count == 1) {
            out.print(in.next());
            return;
        }

        long numbers[] = new long[count];

        long min = Long.parseLong(in.next());
        long max = numbers[0] = min;

        for (int i = 1; i < count; i++) {
            long num = Long.parseLong(in.next());

            if (num < min) {
                min = num;
            }
            if (num > max) {
                max = num;
            }
            numbers[i] = num;
        }

        int length = (int) (max - min + 1);
        long sortNumbers[] = new long[length];

        for (int i = 0; i < count; i++) {
            int idx = (int) (numbers[i] - min);
            sortNumbers[idx]++;
        }

        for (int i = 0; i < length; i++) {
            if (sortNumbers[i] > 0) {
                for (int j = 0; j < sortNumbers[i]; j++) {
                    out.print(i + min + " ");
                }
            }

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
