import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Arrays;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Heapify {
    private Heapify() {
        // Should not be instantiated
    }

    //Решение: https://www.eolymp.com/ru/submissions/11699011
    private static void solve(final FastScanner in, final PrintWriter out) {
        Heap heap = new Heap();
        int n = in.nextInt();
        int commandId;
        for (int i = 0; i < n; i++) {
            commandId = in.nextInt();
            if (commandId == 0) {
                heap.add(in.nextInt());
            } else {
                out.println(heap.remove());
            }
        }
    }

    static class Heap {
        private static final int ROOT_INDEX = 1;
        private static final int DEFAULT_CAPACITY = 16;
        private int[] values = new int[DEFAULT_CAPACITY];
        private int size;

        public void add(int element) {
            if (size == values.length - 1) {
                values = Arrays.copyOf(values, values.length * 3 / 2);
            }
            values[++size] = element;
            swim(size);
        }

        public int remove() {
            int root = values[ROOT_INDEX];
            swap(ROOT_INDEX, size--);
            sink(ROOT_INDEX);
            return root;
        }

        private void swim(int index) {
            int currIndex = index;
            while (currIndex > 1 && values[currIndex] > values[currIndex / 2]) {
                swap(currIndex, currIndex / 2);
                currIndex /= 2;
            }
        }

        private void sink(int index) {
            int currIndex = index;
            int childIndex;
            while (2 * currIndex <= size) {
                childIndex = 2 * currIndex;
                if (childIndex < size && values[childIndex] < values[childIndex + 1]) {
                    childIndex++;
                }
                if (values[currIndex] >= values[childIndex]) {
                    break;
                }
                swap(childIndex, currIndex);
                currIndex = childIndex;
            }
        }

        private void swap(int firstIndex, int secondIndex) {
            int temp = values[firstIndex];
            values[firstIndex] = values[secondIndex];
            values[secondIndex] = temp;
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
