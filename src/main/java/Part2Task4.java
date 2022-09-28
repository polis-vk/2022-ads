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
public final class Part2Task4 {
    private Part2Task4() {
        // Should not be instantiated
    }

    private static void quickSort(int[] a, int from, int to) {
        if (from + 1 >= to) {
            return;
        }
        int i = partition(a, from, to);
        quickSort(a, from, i);
        quickSort(a, i + 1, to);
    }

    private static int partition(int[] a, int from, int to) {
        int i = ThreadLocalRandom.current().nextInt(to - from) + from;
        swap(a, from, i);
        int pivotal = a[from];
        i = from;
        for (int j = from + 1; j < to; j++) {
            if (a[j] <= pivotal) {
                swap(a, ++i, j);
            }
        }
        swap(a, i, from);
        return i;
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        n = in.nextInt();
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            b[i] = in.nextInt();
        }

        quickSort(a, 0, a.length);
        quickSort(b, 0, b.length);

        int i = 0;
        int j = 0;
        boolean isSimilar = true;
        while (i < a.length && j < b.length) {
            while (i + 1 < a.length && a[i + 1] == a[i]) {
                i++;
            }
            while (j + 1 < b.length && b[j + 1] == b[j]) {
                j++;
            }
            if (a[i] != b[j]) {
                isSimilar = false;
                break;
            }
            i++;
            j++;
        }
        if (i < a.length || j < b.length) {
            isSimilar = false;
        }
        out.println(isSimilar ? "YES" : "NO");
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
