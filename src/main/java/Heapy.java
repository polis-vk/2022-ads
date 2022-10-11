import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Heapy {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int count = in.nextInt();
        MaxHeap heap = new MaxHeap(count);
        int command;
        for (int i = 0; i < count; i++) {
            command = in.nextInt();
            if (command == 0) {
                heap.insert(in.nextInt());
                continue;
            }
            System.out.println(heap.delMax());
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

    private static class MaxHeap {
        private int[] array;
        private int size = 0;

        MaxHeap(int size) {
            array = new int[size + 1];
        }

        public int getSize() {
            return size;
        }

        private void swap(int[] array, int first, int second) {
            int temp = array[first];
            array[first] = array[second];
            array[second] = temp;
        }

        public void swim(int k) {
            int index = k;
            while (index > 1 && array[index] > array[index / 2]) {
                swap(array, index, index / 2);
                index /= 2;
            }
        }

        public void sink(int k) {
            int index = k;
            while (2 * index <= size) {
                int j = 2 * index;
                if (j < size && array[j] < array[j + 1]) j++;
                if (array[index] >= array[j]) break;
                swap(array, index, j);
                index = j;
            }
        }

        public void insert(int element) {
            array[++size] = element;
            swim(size);
        }

        public int delMax() {
            int max = array[1];
            swap(array, 1, size--);
            sink(1);
            return max;
        }

        public int peek() {
            return array[1];
        }
    }
}
