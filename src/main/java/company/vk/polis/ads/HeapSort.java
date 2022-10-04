package company.vk.polis.ads;

import java.io.*;
import java.util.Arrays;
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
    private static void sink(int[] arr, int k, int n) {
        while (k * 2 <= n) {
            int j = k * 2;
            if (j < n && arr[j + 1] > arr[j]) {
                j++;
            }
            if (arr[k] >= arr[j]) {
                break;
            }
            swap(arr, k, j);
            k = j;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void makeHeap(int[] arr) {
        int n = arr.length - 1;
        for (int i = n / 2; i >= 1; i--) {
            sink(arr, i, n);
        }
    }

    private static void heapSort(int[] arr) {
        makeHeap(arr);
        for (int i = 0; i < arr.length - 2; i++) {
            swap(arr, 1, arr.length - i - 1);
            sink(arr, 1, arr.length - i - 2);
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int N = in.nextInt();
        int[] arrayToSort = new int[N + 1];
        for (int i = 0; i < N; i++) {
            arrayToSort[i + 1] = in.nextInt();
        }
        heapSort(arrayToSort);
        for (int i = 1; i < arrayToSort.length - 1; i++) {
            out.print(arrayToSort[i] + " ");
        }
        out.println(arrayToSort[arrayToSort.length - 1]);
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
