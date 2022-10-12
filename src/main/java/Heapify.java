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
public final class Heapify {
    private void Heapify() {
        // Should not be instantiated
    }

    private static class Heap {
        private int[] arrayHeap;
        private int size = 0;

        public Heap(int length) {
            arrayHeap = new int[length + 1];
        }

        public void insert(int number) {
            if (size == arrayHeap.length - 1) {
                arrayHeap = Arrays.copyOf(arrayHeap, arrayHeap.length * 2);
            }
            arrayHeap[++size] = number;
            swim(size);
        }

        public void swim(int index) {
            int currentIndex = index;
            while (currentIndex > 1 && arrayHeap[currentIndex] > arrayHeap[currentIndex / 2]) {
                swap(currentIndex, currentIndex / 2);
                currentIndex /= 2;
            }
        }

        private void swap(int left, int right) {
            int buff = arrayHeap[left];
            arrayHeap[left] = arrayHeap[right];
            arrayHeap[right] = buff;
        }

        public int extract() {
            int result = arrayHeap[1];
            swap(1, size--);
            sink();
            return result;
        }

        private void sink() {
            int currentIndex = 1;
            while (2 * currentIndex <= size) {
                int childIndex = 2 * currentIndex;
                if (childIndex < size && arrayHeap[childIndex] < arrayHeap[childIndex + 1]) {
                    childIndex++;
                }
                if (arrayHeap[currentIndex] >= arrayHeap[childIndex]) {
                    break;
                }
                swap(currentIndex, childIndex);
                currentIndex = childIndex;
            }
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int length = in.nextInt();
        Heap heap = new Heap(length);
        for (int i = 0; i < length; i++) {
            switch (in.nextInt()) {
                case 0 -> heap.insert(in.nextInt());
                case 1 -> out.println(heap.extract());
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
