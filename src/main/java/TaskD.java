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

    private static int partition(int[] intArray, int fromInclusive, int toExclusive) {
        int index = ThreadLocalRandom.current().nextInt(toExclusive - fromInclusive) + fromInclusive;
        int tmp = intArray[fromInclusive];
        intArray[fromInclusive] = intArray[index];
        intArray[index] = tmp;
        int pivotal = intArray[fromInclusive];
        int i = fromInclusive;
        for (int j = fromInclusive + 1; j < toExclusive; j++) {
            if (intArray[j] <= pivotal) {
                tmp = intArray[++i];
                intArray[i] = intArray[j];
                intArray[j] = tmp;
            }
        }
        tmp = intArray[i];
        intArray[i] = intArray[fromInclusive];
        intArray[fromInclusive] = tmp;
        return i;
    }

    private static void quickSort(int[] intArray, int fromInclusive, int toExclusive) {
        if (toExclusive <= fromInclusive)
            return;
        int i = partition(intArray, fromInclusive, toExclusive);
        quickSort(intArray, fromInclusive, i);
        quickSort(intArray, i + 1, toExclusive);
    }


    private static void solve(final FastScanner in, final PrintWriter out) {
         // Read data
        int firstSize = in.nextInt();
        int[] firstArray = new int[firstSize];
        for (int i = 0; i < firstSize; i++) {
            firstArray[i] = in.nextInt();
        }
        int secondSize = in.nextInt();
        int[] secondArray = new int[secondSize];
        for (int i = 0; i < secondSize; i++) {
            secondArray[i] = in.nextInt();
        }
         // Sort arrays
        quickSort(firstArray, 0, firstSize);
        quickSort(secondArray, 0, secondSize);
         // Check for commonness
        int curFirst = 0;
        int curSecond = 0;
        while (curFirst < firstSize && curSecond < secondSize) {
            while (curFirst + 1 < firstSize && firstArray[curFirst] == firstArray[curFirst + 1]) {
                curFirst++;
            }
            while (curSecond + 1 < secondSize && secondArray[curSecond] == secondArray[curSecond + 1]) {
                curSecond++;
            }
            if (firstArray[curFirst] != secondArray[curSecond]) {
                out.print("NO");
                return;
            }
            curFirst++;
            curSecond++;
        }
        if (curFirst == firstSize && curSecond == secondSize) {
            out.print("YES");
        } else {
            out.print("NO");
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
