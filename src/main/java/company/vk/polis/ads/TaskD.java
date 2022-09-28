package company.vk.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class TaskD {
    private TaskD() {
        // Should not be instantiated
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static int partition(int[] a, int fromInclusive, int toExclusive) {
        int i = ThreadLocalRandom.current().nextInt(toExclusive - fromInclusive) + fromInclusive;
        swap(a, fromInclusive, i);
        int pivot = a[fromInclusive];
        i = fromInclusive;
        for (int j = fromInclusive + 1; j < toExclusive; j++) {
            if (pivot > a[j]) {
                swap(a, ++i, j);
            }
        }
        swap(a, i, fromInclusive);
        return i;
    }

    private static boolean areEquivalent(int[] a, int[] b) {
        int i = 0;
        int j = 0;
        int current = 0;

        while (i < a.length && j < b.length) {
            current = a[i];
            if (b[j] != current) {
                return false;
            }
            while (i < a.length && a[i] == current) {
                i++;
            }
            while (j < b.length && b[j] == current) {
                j++;
            }
        }

        while (i < a.length) {
            if (a[i] != current) {
                return false;
            }
            i++;
        }

        while (j < b.length) {
            if (b[j] != current) {
                return false;
            }
            j++;
        }

        return true;
    }

    private static void quickSort(int[] a, int fromInclusive, int toExclusive) {
        if (toExclusive <= fromInclusive + 1) {
            return;
        }

        int pivot = partition(a, fromInclusive, toExclusive);

        quickSort(a, fromInclusive, pivot);
        quickSort(a, pivot + 1, toExclusive);
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        int[] b;

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        n = in.nextInt();
        b = new int[n];

        for (int i = 0; i < n; i++) {
            b[i] = in.nextInt();
        }

        quickSort(a, 0, a.length);
        quickSort(b, 0, b.length);

        out.println(areEquivalent(a, b) ? "YES" : "NO");
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

