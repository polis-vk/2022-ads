package company.vk.polis.ads.paikeee.part3;

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
final class ArrayHeap {
    private ArrayHeap() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Heap myHeap = new Heap(n);
        for (int i = 0; i < n; i++) {
            int command = in.nextInt();
            if (command == 1) {
                int extracted = myHeap.extract();
                out.println(extracted);
            } else {
                myHeap.insert(in.nextInt());
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

    private static class Heap {

        int[] heap;
        int size;

        public Heap(int maxSize) {
            heap = new int[maxSize + 1];
            size = 0;
        }

        void insert(int elem) {
            heap[++size] = elem;
            swim(size);
        }

        int extract() {
            int max = heap[1];
            swap(1, size--);
            sink();
            return max;
        }

        private void sink() {
            int parent = 1;
            while (size >= parent * 2) {
                int child = parent * 2;
                if (child < size && heap[child] < heap[child + 1]) {
                    child++;
                }
                if (heap[parent] >= heap[child]) {
                    break;
                }
                swap(parent, child);
                parent = child;
            }
        }

        private void swim(int child) {
            while (child > 1 && heap[child] > heap[child / 2]) {
                swap(child, child / 2);
                child /= 2;
            }
        }

        private void swap(int a, int b) {
            int temp = heap[a];
            heap[a] = heap[b];
            heap[b] = temp;
        }
    }


    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
