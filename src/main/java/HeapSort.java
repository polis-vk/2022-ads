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
public final class HeapSort {
    private void SolveTemplate() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int length = in.nextInt();
        int[] heap = new int[length + 1];
        for (int i = 1; i < heap.length; i++) {
            heap[i] = in.nextInt();
        }
        heapSort(heap);
        for (int i = 1; i < heap.length; i++) {
            out.print(heap[i] + " ");
        }
    }

    private static void heapSort(int[] heap) {
        int length = heap.length - 1;
        for (int i = length / 2; i >= 1; i--) {
            sink(heap, i, length);
        }
        while (length > 1) {
            swap(heap, 1, length--);
            sink(heap, 1, length);
        }
    }

    private static void swap(int[] heap, int right, int left) {
        int buff = heap[right];
        heap[right] = heap[left];
        heap[left] = buff;
    }

    private static void sink(int[] heap, int parent, int length) {
        while (2 * parent <= length) {
            int child = 2 * parent;
            if (child < length && heap[child] < heap[child + 1]) {
                child++;
            }
            if (heap[parent] >= heap[child]) {
                break;
            }
            swap(heap, parent, child);
            parent = child;
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
