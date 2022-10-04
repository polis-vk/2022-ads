import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Task4 {
    private Task4() {
        // Should not be instantiated
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    private static void quickSort(int[] a, int from, int to, Random random) {

        if (from + 1 >= to) {
            return;
        }
        int i = partition(a, from, to, random);
        quickSort(a, from, i, random);
        quickSort(a, i + 1, to, random);
    }

    private static int partition(int[] a, int fromInclusive, int toExclusive, Random random) {
        int i = random.nextInt(toExclusive - fromInclusive) + fromInclusive;
        swap(a, fromInclusive, i);
        int pivotal = a[fromInclusive];
        i = fromInclusive;
        for (int j = fromInclusive + 1; j < toExclusive; j++) {
            if (a[j] <= pivotal) {
                swap(a, ++i, j);
            }
        }
        swap(a, i, fromInclusive);
        return i;
    }

    public static boolean isSimilar(int[] a, int[] b) {
        int[] arr1 = a;
        int[] arr2 = b;

        int i = 0;
        int j = 0;

        while (i < arr1.length && j < arr2.length) {
            while (i < arr1.length - 1 && arr1[i] == arr1[i + 1]) {
                i++;
            }

            while (j < arr2.length - 1 && arr2[j] == arr2[j + 1]) {
                j++;
            }

            if (arr1[i] != arr2[j]) {
                return false;
            }
            i++;
            j++;
        }
        if (i < arr1.length || j < arr2.length) {
            return false;
        }
        return true;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {

        int N = in.nextInt();
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = in.nextInt();
        }

        N = in.nextInt();
        int[] b = new int[N];
        for (int i = 0; i < N; i++) {
            b[i] = in.nextInt();
        }

        quickSort(a, 0, a.length, new Random());
        quickSort(b, 0, b.length, new Random());

        if (isSimilar(a, b)) {
            out.println("YES");
        } else {
            out.println("NO");
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
