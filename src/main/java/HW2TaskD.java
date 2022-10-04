import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class HW2TaskD {
    private HW2TaskD() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int size = in.nextInt();
        int[] first = new int[size];
        for (int i = 0; i < size; i++) {
            first[i] = in.nextInt();
        }
        size = in.nextInt();
        int[] second = new int[size];
        for (int i = 0; i < size; i++) {
            second[i] = in.nextInt();
        }
        sort(first, 0, first.length);
        sort(second, 0, second.length);
        out.println(areSimilar(first, second) ? "YES" : "NO");
    }

    private static void sort(int[] array, int fromInclusive, int toExclusive) {
        if (fromInclusive >= toExclusive - 1) {
            return;
        }
        int i = partition(array, fromInclusive, toExclusive);
        sort(array, fromInclusive, i);
        sort(array, i + 1, toExclusive);
    }

    private static int partition(int[] array, int fromInclusive, int toExclusive) {
        int pivotal = array[fromInclusive];
        int i = fromInclusive;
        for (int j = fromInclusive + 1; j < toExclusive; ++j) {
            if (array[j] <= pivotal) {
                swap(array, ++i, j);
            }
        }
        swap(array, i, fromInclusive);
        return i;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static boolean areSimilar(int[] first, int[] second) {
        int i = 0;
        int j = 0;
        while (i < first.length && j < second.length) {
            while (i + 1 < first.length && first[i + 1] == first[i]) {
                i++;
            }
            while (j + 1 < second.length && second[j + 1] == second[j]) {
                j++;
            }
            if (first[i] != second[j]) {
                return false;
            }
            i++;
            j++;
        }
        return (i == first.length && j == second.length);
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
