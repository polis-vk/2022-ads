package company.vk.polis.ads.part3.tedbear;

import java.io.*;
import java.util.StringTokenizer;

//https://www.eolymp.com/ru/submissions/11671341

public class HeapSort {
    private static void solve(final FastScanner in, final PrintWriter out) {
        // Write me
        int n = in.nextInt();
        int[] array = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            array[i] = in.nextInt();
        }

        makeHeap(array);
        heapSort(array);

        for (int i = 1; i <= n; i++) {
            out.write(array[i] + " ");
        }

    }

    static void makeHeap(int[] a) {
        int n = a.length - 1;
        for (int k = n / 2; k >= 1; k--) {
            sink(a, k, n);
        }
    }

    static void heapSort(int[] a) {
        int n = a.length - 1;
        for (int k = n / 2; k >= 1; k--) {
            sink(a, k, n);
        }
        while (n > 1) {
            swap(a, 1, n--);
            sink(a, 1, n);
        }
    }

    public static void sink(int[] a, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && a[j] < a[j + 1]) {
                j++;
            }
            if (a[k] >= a[j]) {
                break;
            }
            swap(a, k, j);
            k = j;
        }
    }

    public static void swap(int[] array, int i, int j) {
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
