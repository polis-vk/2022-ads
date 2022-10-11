import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class HW3Task3 {
    private HW3Task3() {
        // Should not be instantiated
    }

    //Простая сортировка: https://www.eolymp.com/ru/submissions/11743067
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] array = new int[++n];
        for (int i = 1; i < n; i++) {
            array[i] = in.nextInt();
        }
        heapSort(array);
        out.print(array[1]);
        for (int i = 2; i < n; i++) {
            out.print(" " + array[i]);
        }
    }

    static void heapSort(int[] array) {
        int size = array.length - 1;
        for (int k = size / 2; k >= 1; k--) {
            sink(array, k, size);
        }
        while (size > 1) {
            swap(array, 1, size--);
            sink(array, 1, size);
        }
    }

    static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    static void sink(int[] array, int k, int size) {
        while (2 * k <= size) {
            int j = 2 * k;
            if (j < size && array[j] < array[j + 1]) {
                j++;
            }
            if (array[k] >= array[j]) {
                break;
            }
            swap(array, k, j);
            k = j;
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