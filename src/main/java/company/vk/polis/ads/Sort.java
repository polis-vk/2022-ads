package company.vk.polis.ads;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Sort {
    private Sort() {
        // Should not be instantiated
    }

    static void sink(int[] arr, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;// left child
            if (j < n && arr[j] < arr[j + 1]) {
                j++;
            }
            if (arr[k] >= arr[j]) {
                break;
            }
            swap(arr, k, j);
            k = j;
        }
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static void sort(int[] arr) {
        int n = arr.length - 1;
        for (int i = n / 2; i >= 1; i--) {
            sink(arr, i, n);
        }
        while (n > 1) {
            swap(arr, 1, n--);
            sink(arr, 1, n);
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int length = n + 1;
        int[] arr = new int[length];
        for (int i = 1; i < length; i++) {
            arr[i] = in.nextInt();
        }
        sort(arr);
        for (int i = 1; i < length ; i++) {
            out.print(arr[i] + " ");
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
