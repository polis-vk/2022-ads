package company.vk.polis.ads;

import java.io.*;
import java.util.StringTokenizer;

public class HeapSort {

    //https://www.eolymp.com/ru/submissions/11734576

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        n++;
        int[] array = new int[n];
        for (int i = 1; i < n; i++) {
            int val = in.nextInt();
            array[i] = val;
        }
        heapSort(array);
        for (int i = 1; i < n; i++) {
            out.print(String.valueOf(array[i]) + ' ');
        }
    }

    public static void heapSort(int[] array) {
        int n = array.length - 1;
        for (int k = n / 2; k >= 1; k--) {
            sink(array, k, n);
        }
        while (n > 1) {
            swap(array, 1, n--);
            sink(array, 1, n);
        }
    }

    private static void sink(int[] a, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k; // left child
            if (j < n && a[j] < a[j + 1]) {
                j++; //right child
            }
            if (a[k] >= a[j]) {
                break; // invariant holds
            }
            swap(a, k, j);
            k = j;
        }
    }

    private static void swap(int[] a, int k1, int k2) {
        int temp = a[k1];
        a[k1] = a[k2];
        a[k2] = temp;
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
