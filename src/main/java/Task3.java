import java.io.*;
import java.util.StringTokenizer;

public final class Task3 {
    private Task3() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] array = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            array[i] = in.nextInt();
        }
        Heap heap = new Heap(array, n);
        heap.heapSort();
        array = heap.array;
        StringBuilder ans = new StringBuilder();
        for (int i = 1; i < n + 1; i++) {
            ans.append(array[i]);
            ans.append(" ");
        }
        out.println(ans);
    }

    private static final class Heap {
        private int[] array;
        private int n;

        public Heap(int[] array, int n) {
            this.array = array;
            this.n = n;
        }

        public void heapSort() {
            for (int k = n / 2; k >= 1; k--) {
                sink(k);
            }
            while (n > 1) {
                swap(1, n--);
                sink(1);
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
