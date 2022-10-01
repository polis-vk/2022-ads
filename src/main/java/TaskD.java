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

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] firstArray = new int[n];
        for (int i = 0; i < n; i++) {
            firstArray[i] = in.nextInt();
        }

        int m = in.nextInt();
        int[] secondArray = new int[m];
        for (int i = 0; i < m; i++) {
            secondArray[i] = in.nextInt();
        }

        quickSort(firstArray, 0, n);
        quickSort(secondArray, 0, m);
        out.println(areSimilar(firstArray, secondArray) ? "YES" : "NO");
    }

    private static void quickSort(int[] array, int fromInclusive, int toExclusive) {
        if (fromInclusive >= toExclusive - 1) {
            return;
        }
        int i = partition(array, fromInclusive, toExclusive);
        quickSort(array, fromInclusive, i);
        quickSort(array, i + 1, toExclusive);
    }

    private static int partition(int[] array, int fromInclusive, int toExclusive) {
        int i = ThreadLocalRandom.current().nextInt(toExclusive - fromInclusive) + fromInclusive;
        swap(array, i, fromInclusive);
        int pivotal = array[fromInclusive];
        i = fromInclusive;
        for (int j = i + 1; j < toExclusive; j++) {
            if (array[j] <= pivotal) {
                swap(array, ++i, j);
            }
        }
        swap(array, i, fromInclusive);
        return i;
    }

    private static boolean areSimilar(int[] firstArray, int[] secondArray) {
        int i = 0;
        int j = 0;
        while (i < firstArray.length && j < secondArray.length) {
            i = getLastIndexOfSimilar(firstArray, i);
            j = getLastIndexOfSimilar(secondArray, j);
            if (firstArray[i] != secondArray[j]) {
                return false;
            }
            i++;
            j++;
        }
        return i >= firstArray.length && j >= secondArray.length;
    }

    private static int getLastIndexOfSimilar(int[] array, int index) {
        int indexCopy = index;
        while (indexCopy < array.length - 1 && array[indexCopy] == array[indexCopy + 1]) {
            indexCopy++;
        }
        return indexCopy;
    }

    private static void swap(int[] array, int i, int j) {
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

