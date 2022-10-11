import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class HW3Task1 {
    private HW3Task1() {
        // Should not be instantiated
    }

    //Хипуй: https://www.eolymp.com/ru/submissions/11743022
    private static void solve(final FastScanner in, final PrintWriter out) {
        Heap heap = new Heap();
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int command = in.nextInt();
            if (command == 0) {
                int num = in.nextInt();
                heap.insert(num);
            } else if (command == 1) {
                out.println(heap.delMax());
            }
        }
    }

    private static class Heap {

        int[] array = new int[10];
        int size;

        void swap(int[] array, int i, int j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

        void swim(int k) {
            while (k > 1 && array[k] > array[k / 2]) {
                swap(array, k, k / 2);
                k = k / 2;
            }
        }

        void sink(int k) {
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

        void insert(int v) {
            if (size + 1 >= array.length) {
                increaseArraySize();
            }
            array[++size] = v;
            swim(size);
        }

        int delMax() {
            int max = array[1];
            swap(array, 1, size--);
            sink(1);
            return max;
        }

        void increaseArraySize() {
            array = Arrays.copyOf(array, array.length * 3 / 2 + 1);
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
