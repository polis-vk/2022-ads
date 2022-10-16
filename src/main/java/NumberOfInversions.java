import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class NumberOfInversions {
    private NumberOfInversions() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int sizeArray = in.nextInt();
        int[] array = new int[sizeArray];
        for (int i = 0; i < sizeArray; i++) {
            array[i] = in.nextInt();
        }
        int result = sort(array, sizeArray);
        out.println(result);
    }

    private static int sort(int[] array, int size) {
        if (size < 2) {
            return 0;
        }
        int mid = size / 2;
        int[] leftArray = new int[mid];
        int[] rightArray = new int[size - mid];
        for (int i = 0; i < mid; i++) {
            leftArray[i] = array[i];
        }
        for (int i = mid; i < size; i++) {
            rightArray[i - mid] = array[i];
        }
        return sort(leftArray, mid) + sort(rightArray, size - mid) + merge(array, leftArray, rightArray, mid, size - mid);
    }

    private static int merge(int[] array, int[] leftArray, int[] rightArray, int left, int right) {
        int i = 0;
        int j = 0;
        int k = 0;
        int counterInversions = 0;
        int counterLeft = left;
        while (i < left && j < right) {
            if (leftArray[i] < rightArray[j]) {
                array[k] = leftArray[i];
                k++;
                i++;
                counterLeft--;
            } else {
                counterInversions += counterLeft;
                array[k] = rightArray[j];
                k++;
                j++;
            }
        }
        while (i < left) {
            array[k] = leftArray[i];
            k++;
            i++;
        }
        while (j < right) {
            array[k] = rightArray[j];
            k++;
            j++;
        }
        return counterInversions;
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
