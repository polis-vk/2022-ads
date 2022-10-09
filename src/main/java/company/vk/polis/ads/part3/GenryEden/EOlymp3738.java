package company.vk.polis.ads.part3.GenryEden;

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

// submission: https://www.eolymp.com/ru/submissions/11716711
public final class EOlymp3738 {
    private EOlymp3738() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }
        heapSort(arr);
        for (int c: arr) {
            out.print(c + " ");
        }
    }

    private static void heapSort(int[] arr) {

        heapify(arr);
        int pointer = arr.length - 1;
        while (pointer > 0){
            swap(arr, 0, pointer--);
            sink(arr, 0, pointer);
        }
    }

    private static void heapify(int[] arr) {
        for (int i = arr.length; i >= 0; i--) {
            if (i * 2 + 1 < arr.length) {
                sink(arr, i, arr.length - 1);
            }
        }

    }

    private static void sink(int[] arr, int index, int limit) {
        int leftChildIndex = index * 2 + 1;
        int rightChildIndex = index * 2 + 2;
        if (rightChildIndex > limit) {
            if (leftChildIndex <= limit && arr[leftChildIndex] > arr[index]) {
                swap(arr, leftChildIndex, index);
                sink(arr, leftChildIndex, limit);
            }
            return;
        }
        if (leftChildIndex <= limit && arr[leftChildIndex] > arr[index] && (arr[leftChildIndex] > arr[rightChildIndex])) {
            swap(arr, leftChildIndex, index);
            sink(arr, leftChildIndex, limit);
        } else if (rightChildIndex <= limit && arr[rightChildIndex] > arr[index]) {
            swap(arr, rightChildIndex, index);
            sink(arr, rightChildIndex, limit);
        }
    }

    private static void swap(int[] arr, int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
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
