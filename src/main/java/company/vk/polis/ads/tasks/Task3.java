package company.vk.polis.ads.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Task3 {
    private Task3() {
        // Should not be instantiated
    }

    private static void sink(int[] array, int K, int N) {

        while (K * 2 <= N) {
            int j = K * 2;
            if (j < N && array[j + 1] > array[j]) {
                j++;
            }
            if (array[K] >= array[j]) {
                break;
            }
            swap(array, K, j);
            K = j;
        }
    }

    private static void makeHeap(int[] array) {
        int N = array.length - 1;

        for (int i = N / 2; i >= 1; i--) {
            sink(array, i, N);
        }
    }

    private static void sort(int[] array) {
        makeHeap(array);

        for (int i = 0; i < array.length - 2; i++) {
            swap(array, 1, array.length - i - 1);
            sink(array, 1, array.length - i - 2);
        }

    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int N = in.nextInt();

        int[] array = new int[N + 1];

        for (int i = 0; i < N; i++) {
            array[i + 1] = in.nextInt();
        }

        sort(array);

        for (int i = 1; i < array.length - 1; i++) {
            out.print(array[i] + " ");
        }

        out.println(array[array.length - 1]);
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
