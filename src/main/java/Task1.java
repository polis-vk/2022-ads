import java.io.*;
import java.util.StringTokenizer;

public final class Task1 {
    private Task1() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Heap heap = new Heap(100000);
        for (int i = 0; i < n; i++) {
            int command = in.nextInt();
            if (command == 0) {
                heap.insert(in.nextInt());
            } else {
                out.println(heap.extract());
            }
        }
    }

    private static final class Heap {
        private int[] array;
        private int n;

        public Heap(int capacity) {
            this.array = new int[capacity + 1];
        }

        public void insert(int x) {
            array[++n] = x;
            swim(n);
        }

        public int extract() {
            int max = array[1];
            swap(1, n--);
            sink(1);
            return max;
        }

        private void swim(int k) {
            while (k > 1 && array[k] > array[k/2]) {
                swap(k, k/2);
                k = k/2;
            }
        }

        private void sink(int k) {
            while (2 * k <= n) {
                int j = 2 * k;
                if (j < n && array[j] < array[j + 1]) j++;
                if (array[k] >= array[j]) break;
                swap(k, j);
                k = j;
            }
        }

        private void swap(int k1, int k2) {
            int temp = array[k1];
            array[k1] = array[k2];
            array[k2] = temp;
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
