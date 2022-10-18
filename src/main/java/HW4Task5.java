import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class HW4Task5 {
    private HW4Task5() {
        // Should not be instantiated
    }

    private static int[] b;

    // Количество инверсий: https://www.eolymp.com/ru/submissions/11843882
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        b = new int[n];
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = in.nextInt();
        }
        out.println(countInv(A, 0, n - 1));
    }

    private static int countInv(int[] a, int i, int j) {
        if (j - i <= 0) {
            return 0;
        }
        int m = (i + j) / 2;
        return countInv(a, i, m) + countInv(a, m + 1, j) + countSplitInv(a, i, j, m + 1);
    }

    private static int countSplitInv(int[] a, int left, int right, int mid) {
        int i = 0;
        int j = left;
        int k = mid;
        int count = 0;
        while (j < mid && k <= right) {
            if (a[j] > a[k]) {
                b[i++] = a[k++];
                count += mid - j;
            } else {
                b[i++] = a[j++];
            }
        }
        while (j < mid) {
            b[i++] = a[j++];
        }
        while (k <= right) {
            b[i++] = a[k++];
        }
        i = 0;
        while (left <= right) {
            a[left++] = b[i++];
        }
        return count;
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
