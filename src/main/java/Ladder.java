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
public final class Ladder {
    private Ladder() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int size = in.nextInt();
        int[] array = new int[size + 2];
        array[0] = 0;
        array[array.length - 1] = 0;
        for (int i = 1; i <= size; i++) {
            array[i] = in.nextInt();
        }
        int maxStep = in.nextInt();
        int[] result = new int[size + 2];
        result[0] = 0;
        result[1] = result[0] + array[1];
        for (int i = 2; i < result.length; i++) {
            result[i] = findMax(result, maxStep, i - 1) + array[i];
        }
        out.println(result[result.length - 1]);
    }

    private static int findMax(int[] result, int maxStep, int index) {
        int max = result[index];
        int counter = 0;
        while (counter < maxStep && index >= 0) {
            if (result[index] > max) {
                max = result[index];
            }
            counter++;
            index--;
        }
        return max;
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
