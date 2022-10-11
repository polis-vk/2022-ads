import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Task2Median {
    private Task2Median() {
        // Should not be instantiated
    }

    private static final int AMOUNT_OF_NUMBERS = 1000000;

    private static class Heap {
        private final int[] array;
        private final boolean isMaxHeap;
        private int size;

        public Heap(int capacity, boolean isMax) {
            this.size = 0;
            array = new int[capacity + 1];
            this.isMaxHeap = isMax;
        }

        public int peek() {
            return array[1];
        }

        public int size() {
            return size;
        }

        public void insert(int x) {
            array[++size] = x;
            swim(size);
        }

        public int extract() {
            int max = array[1];
            swap(1, size--);
            sink(1);
            return max;
        }

        private void swim(int k) {
            while (k > 1 && ((isMaxHeap && array[k] > array[k / 2]) || (!isMaxHeap && array[k] < array[k / 2]))) {
                swap(k, k / 2);
                k = k / 2;
            }
        }

        private void sink(int k) {
            while (2 * k <= size) {
                int j = 2 * k;
                if (j < size && ((isMaxHeap && array[j] < array[j + 1]) || (!isMaxHeap && array[j] > array[j + 1])))
                    j++;
                if (((isMaxHeap && array[k] >= array[j]) || (!isMaxHeap && array[k] <= array[j]))) break;
                swap(k, j);
                k = j;
            }
        }

        private void swap(int i, int j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Heap leftHeap = new Heap(AMOUNT_OF_NUMBERS + 1, false);
        Heap rightHeap = new Heap(AMOUNT_OF_NUMBERS + 1, true);

        int x;
        String str = in.next();
        while (str != null) {

            x = Integer.parseInt(str);

            if (rightHeap.peek() > x) {
                rightHeap.insert(x);
            } else {
                leftHeap.insert(x);
            }

            if (rightHeap.size() > leftHeap.size()) {
                leftHeap.insert(rightHeap.extract());
            }
            if (leftHeap.size() > rightHeap.size()) {
                rightHeap.insert(leftHeap.extract());
            }

            x = rightHeap.size() - leftHeap.size();
            if (x == 0) {
                out.println(rightHeap.peek() + ((leftHeap.peek() - rightHeap.peek()) / 2));
            } else {
                out.println(x > 0 ? rightHeap.peek() : leftHeap.peek());
            }

            str = in.next();
        }

    }


    private static final class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            String line = null;
            try {
                line = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return line;
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        public String nextLine() {
            try {
                return reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}