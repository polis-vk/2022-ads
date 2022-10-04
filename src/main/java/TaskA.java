import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
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
        int size = in.nextInt();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = in.nextInt();
        }
        reverseCountingSort(array);
        Arrays.stream(array).forEach(s -> System.out.println(s + " "));
    }

    private static void reverseCountingSort(int[] array) {
        int minNumber = Integer.MAX_VALUE;
        int maxNumber = Integer.MIN_VALUE;
        for (int num : array) {
            if (minNumber > num) {
                minNumber = num;
            }
            if (maxNumber < num) {
                maxNumber = num;
            }
        }

        int[] numCounts = new int[maxNumber - minNumber + 1];
        for (int num : array) {
            numCounts[num - minNumber]++;
        }

        int index = 0;
        for (int i = 0; i < numCounts.length; i++) {
            while (numCounts[i] > 0) {
                array[index] = i + minNumber;
                numCounts[i]--;
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
