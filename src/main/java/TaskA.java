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
public final class TaskA {
    private TaskA() {
        // Should not be instantiated
    }

    private static final int MAX_DIFF = 107;

    private static void solve(final FastScanner in, final PrintWriter out) {
        // Read N
        int numAmount = in.nextInt();
        // Read next N numbers and find min
        int[] intArray = new int[numAmount];
        int minNumber = Integer.MAX_VALUE;
        for (int i = 0; i < numAmount; i++) {
            int num = in.nextInt();
            if (num < minNumber) {
                minNumber = num;
            }
            intArray[i] = num;
        }
        // Do a counting sort
        int[] countArray = new int[MAX_DIFF + 1];
        for (int elem : intArray) {
            countArray[elem - minNumber]++;
        }
        for (int i = 0; i < countArray.length; i++) {
            while (countArray[i] > 0) {
                out.print(i + minNumber + " ");
                countArray[i]--;
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
