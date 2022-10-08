package part3;

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
public final class HeapSortTask {

    private HeapSortTask() {
        // Should not be instantiated
    }

    public static class Heap {

        private final int[] heapArray;

        public Heap(int[] array) {
            this.heapArray = array;
            int n = heapArray.length - 1;
            for (int k = n / 2; k >= 1; k--) {
                sink(heapArray, k, n);
            }
        }

        private void sort() {
            int n = heapArray.length - 1;
            while (n > 1) {
                swap(heapArray, 1, n--);
                sink(heapArray, 1, n);
            }
        }

        private void sink(int[] array, int k, int n) {
            while (2 * k <= n) {
                int j = 2 * k;
                if (j < n && array[j] < array[j + 1]) {
                    j++;
                }
                if (array[k] >= array[j]) {
                    break;
                }
                swap(array, k, j);
                k = j;
            }
        }

        private void swap(int[] heapArray, int index, int parentIndex) {
            int temp = heapArray[index];
            heapArray[index] = heapArray[parentIndex];
            heapArray[parentIndex] = temp;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] array = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            array[i] = in.nextInt();
        }
        Heap heap = new Heap(array);
        heap.sort();
        for (int i = 1; i < n; i++) {
            out.print(array[i] + " ");
        }
        out.print(array[n]);
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
