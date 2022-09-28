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
public final class Part2Task1 {
    private Part2Task1() {
        // Should not be instantiated
    }

    private static final int COUNT_ARRAY_SIZE = 215;

    private static int getCountArrayIndex(int number, int centralNumber) {
        return number - centralNumber + COUNT_ARRAY_SIZE / 2;
    }

    private static int getNumber(int index, int centralNumber) {
        return index + centralNumber - COUNT_ARRAY_SIZE / 2;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int[] countArray = new int[COUNT_ARRAY_SIZE];
        int N = in.nextInt();
        int number = in.nextInt();
        int centralNumber = number;
        countArray[getCountArrayIndex(number, centralNumber)]++;
        for (int i = 0; i < N - 1; i++) {
            number = in.nextInt();
            countArray[getCountArrayIndex(number, centralNumber)]++;
        }
        for (int i = 0; i < COUNT_ARRAY_SIZE; i++) {
            for (int j = 0; j < countArray[i]; j++) {
                out.print(getNumber(i, centralNumber) + " ");
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
