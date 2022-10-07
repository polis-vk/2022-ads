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
public final class HeapSort {
    private HeapSort() {
        // Should not be instantiated
    }

    //Решение: https://www.eolymp.com/ru/submissions/11701054
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt() + 1;
        int[] numbers = new int[n];
        for (int i = 1; i < n; i++) {
            numbers[i] = in.nextInt();
        }

        heapSort(numbers);
        for (int i = 1; i < n; i++) {
            out.print(numbers[i] + " ");
        }
    }

    public static void heapSort(int[] array) {
        int unsortedCount = array.length - 1;
        for (int i = unsortedCount / 2; i >= 1; i--) {
            sink(array, i, unsortedCount);
        }

        while (unsortedCount > 1) {
            swap(array, 1, unsortedCount--);
            sink(array, 1, unsortedCount);
        }
    }

    private static void sink(int[] array, int index, int size) {
        int currIndex = index;
        int childIndex;
        while (2 * currIndex <= size) {
            childIndex = 2 * currIndex;
            if (childIndex < size && array[childIndex] < array[childIndex + 1]) {
                childIndex++;
            }
            if (array[currIndex] >= array[childIndex]) {
                break;
            }
            swap(array, childIndex, currIndex);
            currIndex = childIndex;
        }
    }

    private static void swap(int[] array, int firstIndex, int secondIndex) {
        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
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
