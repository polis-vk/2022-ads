import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Task1Heap {
    private Task1Heap() {
        // Should not be instantiated
    }

    private static int n = 0;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int N = in.nextInt();
        int command;
        int[] array = new int[N + 1];
        for (int i = 0; i < N; i++) {
            command = in.nextInt();
            switch (command) {
                case 0 -> insert(in.nextInt(), array);
                case 1 -> out.println(extract(array));
            }
        }
    }

    private static void insert(int element, int[] array) {
        array[++n] = element;
        swim(n, array);
    }

    private static int extract(int[] array) {
        int max = array[1];
        swap(array, 1, n--);
        sink(1, array);
        return max;
    }

    private static void swim(int k, int[] array) {
        while (k > 1 && array[k] > array[k / 2]) {
            swap(array, k, k / 2);
            k = k / 2;
        }
    }

    private static void sink(int k, int[] array) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && array[j] < array[j + 1]) j++;
            if (array[k] >= array[j]) break;
            swap(array, k, j);
            k = j;
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
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
