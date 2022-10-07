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
public final class HeapSimpleCommands {

    private static final float GOLDEN_RATIO = (float) 1.62;

    private HeapSimpleCommands() {
        // Should not be instantiated
    }

    private static class Heap {

        private int[] storage;
        private int heapSize;

        private int capacity;

        public Heap() {
            this(10);
        }

        public Heap(int size) {
            capacity = size * 2;
            storage = new int[capacity];
            heapSize = 0;
        }

        public void insert(int x) {
            if (heapSize + 1 > capacity) {
                resize();
            }
            storage[heapSize++] = x;
            swim(heapSize - 1);
        }

        public void sink(int k) {
            int left = getLeft(k);
            int right = getRight(k);
            int largest = k;

            if (left < heapSize && storage[left] > storage[k]) {
                largest = left;
            }

            if (right < heapSize && storage[right] > storage[largest]) {
                largest = right;
            }

            if (largest != k) {
                swap(largest, k);
                sink(largest);
            }
        }

        public int extract() {
            int max = storage[0];
            swap(--heapSize, 0);
            sink(0);
            return max;
        }

        private void swim(int k) {
            while (k > 0 && storage[k] > storage[getParent(k)]) {
                swap(k, getParent(k));
                k = getParent(k);
            }
        }

        private int getParent(int k) {
            return (k - 1) / 2;
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

        private void resize() {
            int newCapacity = (int) (capacity * GOLDEN_RATIO);
            int[] tmp = new int[newCapacity];
            for (int i = 0; i < capacity; ++i) {
                tmp[i] = storage[i];
            }
            capacity = newCapacity;
            storage = tmp;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Heap heap = new Heap();
        for (int i = 0; i < n; ++i) {
            int command = in.nextInt();
            if (command == 0) {
                int val = in.nextInt();
                heap.insert(val);
            } else {
                out.println(heap.extract());
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
