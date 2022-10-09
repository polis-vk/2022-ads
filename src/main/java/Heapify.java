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
// https://www.eolymp.com/ru/submissions/11720540
public final class Heapify {
    private Heapify() {
        // Should not be instantiated
    }

    private static final int INSERT = 0;
    private static final int EXTRACT = 1;

    private static class Heap {
        private int[] array;
        private int size;
        private int maxSize;

        public Heap(int size) {
            this.maxSize = size;
            this.size = 0;
            array = new int[this.maxSize + 1];
        }

        public void insert(int value) {
            if (maxSize - 1 == size) {
                this.maxSize *= 2;
                array = Arrays.copyOf(array, array.length * 2);
            }
            array[++size] = value;
            swim(size);
        }

        public int extract() {
            int max = array[1];
            swap(array, 1, size--);
            sink(1);
            return max;
        }

        private void swap(int[] array, int i, int j) {
            int tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }

        private void swim(int k) {
            while (k > 1 && array[k] > array[k / 2]) {
                swap(array, k, k / 2);
                k = k / 2;
            }
        }

        private void sink(int k) {
            while (2 * k <= size) {
                int j = 2 * k;
                if (j < size && array[j] < array[j + 1]) {
                    j++;
                }
                if (array[k] >= array[j]) {
                    break;
                }
                swap(array, k, j);
                k = j;
            }
        }

    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Heap maxHeap = new Heap(n);
        for (int i = 0; i < n; i++) {
            switch (in.nextInt()) {
                case INSERT:
                    maxHeap.insert(in.nextInt());
                    break;
                case EXTRACT:
                    out.println(maxHeap.extract());
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
