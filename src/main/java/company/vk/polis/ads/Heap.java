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
public final class Heap {
    private Heap() {
        // Should not be instantiated
    }

    private static class CustomHeap {
        private final static int INITIAL_SIZE = 16;
        private int[] heap;
        private int n;

        public CustomHeap() {
            n = 0;
            heap = new int[INITIAL_SIZE];
        }

        public void insert(int x) {
            if (n == heap.length - 1) {
                heap = Arrays.copyOf(heap, heap.length * 2);
            }
            heap[++n] = x;
            swim(n);
        }

        public int extract() {
            int result = heap[1];
            swap(1, n--);
            sink(1);
            return result;
        }

        private void sink(int k) {
            while (k * 2 <= n) {
                int j = k * 2;
                if (j < n && heap[j + 1] > heap[j]) {
                    j++;
                }
                if (heap[k] >= heap[j]) {
                    break;
                }
                swap(k, j);
                k = j;
            }
        }

        private void swim(int k) {
            while (1 < k && heap[k] > heap[k / 2]) {
                swap(k, k / 2);
                k /= 2;
            }
        }
        private void swap(int i, int j) {
            int temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int N = in.nextInt();
        CustomHeap heap = new CustomHeap();
        for (int i = 0; i < N; i++) {
            switch (in.nextInt()) {
                case 0:
                    heap.insert(in.nextInt());
                    break;
                case 1:
                    out.println(heap.extract());
                    break;
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
