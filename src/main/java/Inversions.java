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
// https://www.eolymp.com/ru/submissions/11772430
public final class Inversions {
    private Inversions() {
        // Should not be instantiated
    }

    private static int countSplitInv(int[] array, int l, int mid, int r) {
        // Create temporary arrays
        int[] leftArray = new int[mid - l];
        int[] rightArray = new int[r - mid];
        System.arraycopy(array, l, leftArray, 0, leftArray.length);
        System.arraycopy(array, mid, rightArray, 0, rightArray.length);
        // Merge algorithm
        int curLeft = 0;
        int curRight = 0;
        int curPos = l;
        int invCount = 0;
        while (curLeft < leftArray.length && curRight < rightArray.length) {
            if (leftArray[curLeft] <= rightArray[curRight]) {
                array[curPos++] = leftArray[curLeft++];
            } else {
                invCount += mid - (l + curLeft);
                array[curPos++] = rightArray[curRight++];
            }
        }
        while (curLeft < leftArray.length) {
            array[curPos++] = leftArray[curLeft++];
        }
        while (curRight < rightArray.length) {
            array[curPos++] = rightArray[curRight++];
        }
        return invCount;
    }

    private static int countInv(int[] array, int l, int r) {
        if (l == r - 1) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return countInv(array, l , mid) + countInv(array, mid, r) + countSplitInv(array, l, mid, r);
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int [] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }
        out.println(countInv(array, 0, n));
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
