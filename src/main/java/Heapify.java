import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Heapify {
    private Heapify() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int numberCommands = in.nextInt();
        Heap heap = new Heap(numberCommands);
        for (int i = 0; i < numberCommands; i++) {
            int command = in.nextInt();
            if (command == 0) {
                heap.insert(in.nextInt());
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

    public static class Heap {
        private static final int two = 2;
        private int[] heap;
        private int heapSize;


        public Heap(int size) {
            heapSize = 0;
            heap = new int[size];
        }

        private int parent(int i) {
            if (i == 0) {
                return 0;
            }
            return (i - 1) / two;
        }

        private int leftChild(int i) {
            return 2 * i + 1;
        }

        private int rightChild(int i) {
            return 2 * i + 2;
        }

        private void swap(int[] heap, int i, int j) {
            int temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }

        private void heapifyUp(int i) {
            if (i > 0 && heap[parent(i)] < heap[i]) {
                swap(heap, i, parent(i));
                heapifyUp(parent(i));
            }
        }

        private void heapifyDown(int i) {
            int left = leftChild(i);
            int right = rightChild(i);
            int largest = i;
            if (left < heapSize && heap[left] > heap[i]) {
                largest = left;
            }
            if (right < heapSize && heap[right] > heap[largest]) {
                largest = right;
            }
            if (largest != i) {
                swap(heap, i, largest);
                heapifyDown(largest);
            }
        }

        public void insert(int element) {
            heapSize++;
            heap[heapSize - 1] = element;
            heapifyUp(heapSize - 1);
            heapifyUp(heapSize - 1);
        }

        public int extract() {
            int max = heap[0];
            heap[0] = heap[heapSize - 1];
            heapSize--;
            heapifyDown(0);
            return max;
        }
    }
}
