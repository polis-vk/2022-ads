package company.vk.polis.ads;

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
//https://www.eolymp.com/ru/submissions/11722418
public final class HeapSort {
    private HeapSort() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int amount = in.nextInt();
        int[] array = new int[++amount];
        for (int i = 1; i < amount; i++) {
            array[i] = in.nextInt();
        }
        heapSort(array);
        Arrays.stream(array).skip(1).forEach(s -> System.out.print(s + " "));
    }

    public static void heapSort(int[] array) {
        int heapLength = array.length - 1;
        for (int k = heapLength / 2; k >= 1; k--) {
            sink(array, k, heapLength);
        }

        while (heapLength > 1) {
            swap(array, 1, heapLength--);
            sink(array, 1, heapLength);
        }
    }

    private static void sink(int[] array, int parent, int heapSize) {
        while (2 * parent <= heapSize) {
            int child = 2 * parent;
            if (child < heapSize && array[child] < array[child + 1]) {
                child++;
            }
            if (array[parent] >= array[child]) {
                break;
            }
            swap(array, parent, child);
            parent = child;
        }
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
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