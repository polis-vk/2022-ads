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

//https://www.eolymp.com/ru/submissions/11717690
public final class CustomHeap {

    private static final int INIT_CAPACITY = 10;

    private CustomHeap() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Heap heap = new Heap(INIT_CAPACITY);
        int numOfOperations = in.nextInt();
        for (int i = 0; i < numOfOperations; i++) {
            int command = in.nextInt();
            if (command == 0) {
                heap.insert(in.nextInt());
            } else if (command == 1) {
                int result = heap.extract();
                System.out.println(result);
            }
        }
    }

    private static class Heap {
        private int[] array;
        private int heapSize;
        private int arrCapacity;

        public Heap(int arrCapacity) {
            this.arrCapacity = arrCapacity;
            array = new int[arrCapacity];
            heapSize = 0;
        }

        public int extract() {
            int max = array[1];
            swap(array, 1, heapSize--);
            sink(1);
            return max;
        }

        public void insert(int x) {
            if (heapSize + 1 >= arrCapacity) {
                increaseCapacity();
            }
            array[++heapSize] = x;
            swim(heapSize);
        }

        private void swim(int child) {
            while (child > 1 && array[child] > array[child / 2]) {
                swap(array, child, child / 2);
                child /= 2;
            }
        }

        private void sink(int parent) {
            while (2 * parent <= heapSize) {
                int child = 2 * parent;
                if (child < heapSize && array[child] < array[child + 1]) {
                    child++;
                }
                if (array[parent] >= array[child]) {
                    break;
                }
                swap(array, parent, child);
                parent = child;
            }
        }

        private void increaseCapacity() {
            arrCapacity = (arrCapacity * 3 / 2) + 1;
            array = Arrays.copyOf(array, arrCapacity);
        }

        private void swap(int[] arr, int a, int b) {
            int temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
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
