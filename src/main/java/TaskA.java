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
        int[] numbers = new int[in.nextInt()];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = in.nextInt();
        }
        countingSort(numbers);
        for (int elem : numbers) {
            out.print(elem + " ");
        }
    }

    private static void countingSort(int[] array) {
        int minValue = array[0];
        int maxValue = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < minValue) {
                minValue = array[i];
            }
            if (array[i] > maxValue) {
                maxValue = array[i];
            }
        }

        int[] supportArray = new int[maxValue - minValue + 1];
        for (int elem : array) {
            supportArray[elem - minValue]++;
        }

        int index = 0;
        for (int i = 0; i < supportArray.length; i++) {
            while (supportArray[i] > 0) {
                supportArray[i]--;
                array[index] = i + minValue;
                index++;
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

