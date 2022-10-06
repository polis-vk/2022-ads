package company.vk.polis.ads.part3.nikitry;

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
// https://www.eolymp.com/ru/submissions/11688616
public final class HeapSort {

    private static void heapSort(int[] array) {
        int n = array.length - 1;
        makeHeap(array);
        while (n > 1) {
            swap(array, 1, n--);
            sink(array, 1, n);
        }
    }

    private static void makeHeap(int[] array) {
        int n = array.length - 1;
        for (int k = n / 2; k >= 1; k--) {
            sink(array, k, n);
        }
    }

    private static void sink(int[] array, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && array[j] < array[j + 1]) j++;
            if (array[k] > array[j]) break;
            swap(array, k, j);
            k = j;
        }
    }

    private static void swap(int[] array, int k, int j) {
        int temp = array[k];
        array[k] = array[j];
        array[j] = temp;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int[] inputArray = new int[in.nextInt() + 1];
        for (int i = 1; i < inputArray.length; i++) {
            inputArray[i] = in.nextInt();
        }
        heapSort(inputArray);
        for (int i = 1; i < inputArray.length; i++) {
            out.write(inputArray[i] + " ");
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