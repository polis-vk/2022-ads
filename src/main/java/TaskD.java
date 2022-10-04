import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

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
        // Write me
        int firstArraySize = in.nextInt();
        int firstArray[] = new int[firstArraySize];

        for (int i = 0; i < firstArraySize; i++) {
            firstArray[i] = in.nextInt();
        }

        int secondArraySize = in.nextInt();
        int secondArray[] = new int[secondArraySize];

        for (int i = 0; i < secondArraySize; i++) {
            secondArray[i] = in.nextInt();
        }

        quickSort(firstArray, 0, firstArraySize);
        quickSort(secondArray, 0, secondArraySize);

        System.out.println(checkForSimilarity(firstArray, secondArray) ? "YES" : "NO");

    }

    private static boolean checkForSimilarity(int first[], int second[]) {
        int i = 0;
        int j = 0;
        boolean result = true ;

        while (i < first.length && j < second.length) {
            if (first[i] != second[j]) {
                result = false;
                break;
            } else {
                int temp = first[i];
                i++;
                j++;
                if (i >= first.length && j >= second.length) {
                    result = true;
                    break;
                }
                while (i < first.length && first[i] == temp) {
                    i++;
                }
                while (j < second.length && second[j] == temp) {
                    j++;
                }

            }
        }
        if (i >= first.length && j >= second.length) {
            result = true;
        }else {
            result = false;
        }

        return result;
    }

    private static void quickSort(int[] firstArray, int fromInclusive, int toExclusive) {
        if (fromInclusive >= toExclusive - 1) {
            return;
        }
        int i = partition(firstArray, fromInclusive, toExclusive);
        quickSort(firstArray, fromInclusive, i);
        quickSort(firstArray, i + 1, toExclusive);

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