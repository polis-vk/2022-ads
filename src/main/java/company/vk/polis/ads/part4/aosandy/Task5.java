package company.vk.polis.ads.part4.aosandy;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Количество инверсий
 */
public final class Task5 {
    static int[] arrayCopy;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();

        int[] array = new int[n];
        arrayCopy = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }

        out.println(countInv(array, 0, n - 1));
    }

    public static int countInv(int[] a, int i, int j) {
        if (i >= j) {
            return 0;
        }

        int mid = i + (j - i) / 2;

        return countInv(a, i, mid) +
            countInv(a, mid + 1, j) +
            countSplitInv(a, i, j, mid);
    }

    public static int countSplitInv(int[] a, int begin, int end, int mid) {
        int i = begin;
        int j = mid + 1;
        int k = begin;
        int countOfInv = 0;

        while (i <= mid && j <= end) {
            if (a[i] <= a[j]) {
                arrayCopy[k++] = a[i++];
            } else {
                arrayCopy[k++] = a[j++];
                countOfInv += mid - i + 1;
            }
        }
        while (i <= mid) {
            arrayCopy[k++] = a[i++];
        }
        while (j <= end) {
            arrayCopy[k++] = a[j++];
        }
        System.arraycopy(arrayCopy, begin, a, begin, end - begin + 1);
        return countOfInv;
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
