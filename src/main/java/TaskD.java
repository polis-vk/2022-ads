import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

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
        int[] arr1 = new int[in.nextInt()];
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = in.nextInt();
        }
        int[] arr2 = new int[in.nextInt()];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = in.nextInt();
        }

        System.out.println(isSimilar(arr1, arr2) ? "YES" : "NO");
    }

    public static boolean isSimilar(int[] arr1, int[] arr2) {
        int lengthArr1 = arr1.length;
        int lengthArr2 = arr2.length;
        qSort(arr1, 0, lengthArr1);
        qSort(arr2, 0, lengthArr2);

        if (((lengthArr1 == 0) ^ (lengthArr2 == 0))) {
            return false;
        }

        int i = lastSameDigitIndex(arr1, 0);
        int j = lastSameDigitIndex(arr2, 0);
        while (i < lengthArr1 && j < lengthArr2) {
            if (arr1[i] != arr2[j]) {
                return false;
            }
            i = lastSameDigitIndex(arr1, ++i);
            j = lastSameDigitIndex(arr2, ++j);
        }

        return i >= lengthArr1 && j >= lengthArr2;
    }

    private static int lastSameDigitIndex(int[] array, int i) {
        while (i + 1 < array.length && array[i] == array[i + 1]) {
            i++;
        }
        return i;
    }

    private static void qSort(int[] array, int fromInclusive, int toExclusive) {
        if (fromInclusive >= toExclusive - 1) {
            return;
        }
        int i = partition(array, fromInclusive, toExclusive);
        qSort(array, fromInclusive, i);
        qSort(array, i + 1, toExclusive);
    }

    private static int partition(int[] array, int fromInclusive, int toExclusive) {
        int pivotal = array[fromInclusive];
        int i = fromInclusive;
        for (int j = fromInclusive + 1; j < toExclusive; j++) {
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
