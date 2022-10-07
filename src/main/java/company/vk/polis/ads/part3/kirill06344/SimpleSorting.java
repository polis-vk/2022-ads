package company.vk.polis.ads.part3.kirill06344;

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
public final class SimpleSorting {
    private SimpleSorting() {
        // Should not be instantiated
    }

    private static class Heap {
        int[] storage;
        int heapSize;

        public Heap(int[] storage) {
            this.storage = storage;
            heapSize = storage.length;
        }

        private void maxHeapify(int k) {
            int left = getLeft(k);
            int right = getRight(k);
            int largest = k;

            if (right < heapSize && storage[right] > storage[k]) {
                largest = right;
            }

            if (left < heapSize && storage[left] > storage[largest]) {
                largest = left;
            }

            if (largest != k) {
                swap(k, largest);
                maxHeapify(largest);
            }
        }

        public void buildMaxHeapify() {
            for (int i = storage.length / 2; i >= 0; --i) {
                maxHeapify(i);
            }
        }

        private int getLeft(int k) {
            return 2 * k + 1;
        }

        private int getRight(int k) {
            return 2 * k + 2;
        }

        private void swap(int i, int j) {
            int tmp = storage[i];
            storage[i] = storage[j];
            storage[j] = tmp;
        }
    }

    private static void heapSort(int[] arr) {
        Heap heap = new Heap(arr);
        heap.buildMaxHeapify();
        for (int i = arr.length - 1; i > 0; --i) {
            heap.swap(0, i);
            --heap.heapSize;
            heap.maxHeapify(0);
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = in.nextInt();
        }
        heapSort(arr);
        for (int el : arr) {
            out.print(el + " ");
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
