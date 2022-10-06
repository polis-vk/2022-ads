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

// Submission link: https://www.eolymp.com/ru/submissions/11659800
public final class Task3 {
    private Task3() {
        // Should not be instantiated
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void sink(int[] a, int n, int i) {
        int j;

        while (i << 1 <= n) {
            j = i << 1;
            if (j < n && a[j] < a[j + 1]) {
                j++;
            }
            if (a[i] >= a[j]) {
                break;
            }
            swap(a, i, j);
            i = j;
        }
    }

    private static void makeHeap(int[] a, int n) {
        for (int i = n >> 1; i >= 1; i--) {
            sink(a, n, i);
        }
    }

    private static void heapSort(int[] a) {
        int n = a.length - 1;
        int size = n;

        makeHeap(a, n);

        for (int i = 0; i < size; i++) {
            swap(a, 1, n);
            n--;
            sink(a, n, 1);
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n + 1];

        for (int i = 1; i < a.length; i++) {
            a[i] = in.nextInt();
        }

        heapSort(a);

        for (int i = 1; i < a.length; i++) {
            System.out.print(a[i] + " ");
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
