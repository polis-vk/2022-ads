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

// Submission link: https://www.eolymp.com/ru/submissions/11766491
public final class CountInversions {
    private CountInversions() {
        // Should not be instantiated
    }

    private static int countInversionsSplit(int[] a, int[] temp, int fromInclusive, int mid, int toExclusive) {
        int c = 0;
        int i = fromInclusive;
        int j = mid;
        int k = fromInclusive;

        while (i < mid && j < toExclusive) {
            if (a[i] > a[j]) {
                c += mid - i;
                temp[k] = a[j];
                j++;
            } else {
                temp[k] = a[i];
                i++;
            }
            k++;
        }

        while (i < mid) {
            temp[k] = a[i];
            i++;
            k++;
        }

        while (j < toExclusive) {
            temp[k] = a[j];
            j++;
            k++;
        }

        for (i = fromInclusive; i < toExclusive; i++) {
            a[i] = temp[i];
        }

        return c;
    }

    private static int countInversions(int[] a, int[] temp, int fromInclusive, int toExclusive) {
        if (toExclusive - fromInclusive <= 1) {
            return 0;
        }

        int mid = (fromInclusive + toExclusive) / 2;

        return countInversions(a, temp, fromInclusive, mid) + countInversions(a, temp, mid, toExclusive) +
                countInversionsSplit(a, temp, fromInclusive, mid, toExclusive);
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        System.out.println(countInversions(a, new int[n], 0, n));
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
