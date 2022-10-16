package company.vk.polis.ads;

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
public final class CountingOfInversions {
    private CountingOfInversions() {
        // Should not be instantiated
    }

    private static int countInv(int[] a, int l, int r) {
        if (l + 1 >= r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return countInv(a, l, mid) + countInv(a, mid, r) + countSplitInv(a, l, r, mid);
    }

    private static int countSplitInv(int[] a, int l, int r, int mid) {
        int count = 0;
        int[] res = new int[r - l];
        int it1 = 0;
        int it2 = 0;
        while (l + it1 < mid && mid + it2 < r) {
            if (a[l + it1] <= a[mid + it2]) {
                res[it1 + it2] = a[l + it1];
                ++it1;
            } else {
                count += (mid - (l + it1));
                res[it1 + it2] = a[mid + it2];
                ++it2;
            }
        }

        while (l + it1 < mid) {
            res[it1 + it2] = a[l + it1];
            ++it1;
        }

        while (mid + it2 < r) {
            res[it1 + it2] = a[mid + it2];
            ++it2;
        }

        if (r - l >= 0){
            System.arraycopy(res, 0, a, l, r - l);
        }
        return count;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int [] arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = in.nextInt();
        }

        out.print(countInv(arr, 0, n));
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
