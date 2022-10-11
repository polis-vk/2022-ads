import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Task3HeapSort {
    private Task3HeapSort() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int N = in.nextInt();
        int[] array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = in.nextInt();
        }
        heapSort(array);
        for (int i = 0; i < N; i++) {
            out.print(array[i] + " ");
        }
    }

    private static void heapSort(int[] array) {
        int n = array.length - 1;
        for (int k = n / 2; k >= 0; --k) {
            sink(array, k, n);
        }
        while (n > 0) {
            swap(array, 0, n--);
            sink(array, 0, n);
        }
    }

    private static void sink(int[] array, int k, int n) {
        while (2 * k + 1 <= n) {
            int j = 2 * k + 1;
            if (j < n && array[j] < array[j + 1]) ++j;
            if (array[k] >= array[j]) break;
            swap(array, k, j);
            k = j;
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
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
