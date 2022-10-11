package company.vk.polis.ads.part3.vigilanteee;

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
public final class CustomHeapV2Cherepanov {
    private void SolveTemplate() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Heap heap = new Heap(n);
        for (int i = 0; i < n; i++) {
            int command = in.nextInt();
            switch (command) {
                case 0:
                    int number = in.nextInt();
                    heap.insert(number);
                    break;
                case 1:
                    out.println(heap.extract());
            }
        }
    }

    /*
     * Heap with [0..n - 1] indexes
     */
    static class Heap {

        private int[] heap;
        private int size;

        public Heap(int size) {
            this.heap = new int[size];
        }

        public void insert(int x) {
            size++;
            heap[size - 1] = x;
            siftUp(size - 1);
        }

        public int extract() {
            int max = heap[0];
            heap[0] = heap[size - 1];
            size--;
            siftDown(0);
            return max;
        }

        private void swap(int[] arr, int a, int b) {
            int temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
        }

        private void siftUp(int k) {
            while (k > 0 && heap[k] > heap[(k - 1) / 2]) {
                swap(heap, k, (k - 1) / 2);
                k = (k - 1) / 2;
            }
        }

        private void siftDown(int k) {
            while (2 * k + 1 < size) {
                int left = 2 * k + 1;
                int right = 2 * k + 2;
                int j = left;
                if (right < size && heap[j] < heap[right]) {
                    j++;
                }
                if (heap[k] >= heap[j]) {
                    break;
                }
                swap(heap, k, j);
                k = j;
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
