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

    private static void solve(final FastScanner in, final PrintWriter out) {
        // Write me
        int quantity = in.nextInt();
        int array[] = new int[quantity];
        array[0] = in.nextInt();
        int maxValue = array[0];
        int minValue = array[0];

        for (int i = 1; i < quantity; i++) {
            array[i] = in.nextInt();
            if (maxValue < array[i]) {
                maxValue = array[i];
            }
            if (minValue > array[i]) {
                minValue = array[i];
            }
        }

        int sortedArray[][] = countingSort(array, minValue, maxValue);

        for (int i = 0; i < sortedArray[0].length; i++) {
            for (int j = 0; j < sortedArray[1][i]; j++) {
                System.out.print(sortedArray[0][i] + " ");
            }
        }
    }

    private static int[][] countingSort(int[] array, int min, int max) {
        int sortedArray[][] = new int[2][max - min + 1];

        for (int i = 0; i < array.length; i++) {
            sortedArray[0][array[i] - min] = array[i];
            sortedArray[1][array[i] - min] += 1;
        }
        return sortedArray;
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