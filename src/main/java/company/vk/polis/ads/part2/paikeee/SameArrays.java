package company.vk.polis.ads.part2.paikeee;

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
public final class SameArrays {

    private SameArrays() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        int aSize = in.nextInt();
        int[] a = Arrays.stream(in.reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int bSize = in.nextInt();
        int[] b = Arrays.stream(in.reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        quickSort(a, 0, aSize - 1);
        quickSort(b, 0, bSize - 1);
        initRepeated(a, aSize);
        initRepeated(b, bSize);

        if (check(a, aSize - 1, b, bSize - 1)) {
            out.println("YES");
        } else {
            out.println("NO");
        }
    }

    private static boolean check(int[] a, int i, int[] b, int j) {
        while (i >= 0 && j >= 0) {
            while (a[i] == Integer.MAX_VALUE && i > 0) {
                i--;
            }
            while (b[j] == Integer.MAX_VALUE && j > 0) {
                j--;
            }
            if (a[i] != b[j]) {
                return false;
            }
            i--;
            j--;
        }
        return i <= 0 && j <= 0;
    }

    private static void initRepeated(int[] array, int size) {
        for (int i = size - 1; i > 0; i--) {
            if (array[i] == array[i - 1]) {
                array[i] = Integer.MAX_VALUE;
            }
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

    private static void quickSort(int[] source, int leftBorder, int rightBorder) {
        int leftMarker = leftBorder;
        int rightMarker = rightBorder;
        int pivot = source[(leftMarker + rightMarker) / 2];
        do {
            while (source[leftMarker] < pivot) {
                leftMarker++;
            }
            while (source[rightMarker] > pivot) {
                rightMarker--;
            }
            if (leftMarker <= rightMarker) {
                if (leftMarker < rightMarker) {
                    int tmp = source[leftMarker];
                    source[leftMarker] = source[rightMarker];
                    source[rightMarker] = tmp;
                }
                leftMarker++;
                rightMarker--;
            }
        } while (leftMarker <= rightMarker);

        if (leftMarker < rightBorder) {
            quickSort(source, leftMarker, rightBorder);
        }
        if (leftBorder < rightMarker) {
            quickSort(source, leftBorder, rightMarker);
        }
    }

    public static void main(final String[] arg) throws IOException {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}

