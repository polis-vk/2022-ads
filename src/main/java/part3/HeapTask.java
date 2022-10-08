package part3;

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

public final class HeapTask {
    private HeapTask() {
        // Should not be instantiated
    }

    private static class Heap {

        private int[] heapArray;
        private int currentSize;

        public Heap(int size) {
            this.currentSize = 0;
            this.heapArray = new int[size + 1];
        }

        public void insert(int value) {
            if (currentSize == heapArray.length) {
                heapArray = Arrays.copyOf(heapArray, heapArray.length * 2);
            }
            heapArray[++currentSize] = value;
            swim(currentSize);
        }

        public int exctract() {
            if (currentSize == 0) {
                throw new RuntimeException();
            }
            if (currentSize == 1) {
                currentSize--;
                return heapArray[1];
            }
            if (currentSize >= heapArray.length - 1) {
                heapArray = Arrays.copyOf(heapArray, heapArray.length * 2);
            }
            int root = heapArray[1];
            swap(heapArray, 1, currentSize--);
            sink(1);
            return root;
        }

        private void swim(int k) {
            while (k > 1 && heapArray[k] > heapArray[k / 2]) {
                swap(heapArray, k, k / 2);
                k = k / 2;
            }
        }

        private void sink(int k) {
            while (2 * k <= currentSize) {
                int j = 2 * k;
                if (j < currentSize && heapArray[j] < heapArray[j + 1]) {
                    j++;
                }
                if (heapArray[k] >= heapArray[j]) {
                    break;
                }
                swap(heapArray, k, j);
                k = j;
            }
        }

        private void swap(int[] heapArray, int index, int parentIndex) {
            int temp = heapArray[index];
            heapArray[index] = heapArray[parentIndex];
            heapArray[parentIndex] = temp;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int countOfCommands = in.nextInt();
        Heap heap = new Heap(countOfCommands);
        while (countOfCommands != 0) {
            switch (in.next()) {
                case "0" -> {
                    heap.insert(in.nextInt());
                    countOfCommands--;
                }
                case "1" -> {
                    try {
                        out.println(heap.exctract());
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                    }
                    countOfCommands--;
                }
                default -> System.exit(-1);
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
