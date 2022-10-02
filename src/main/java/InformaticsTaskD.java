import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class InformaticsTaskD {
    private InformaticsTaskD() {
        // Should not be instantiated
    }

    private static void sort(int[] arr, int fromInclusive, int toExclusive) {
        if (fromInclusive >= toExclusive - 1) {
            return;
        }
        int pos = partition(arr, fromInclusive, toExclusive);
        sort(arr, fromInclusive, pos);
        sort(arr, pos + 1, toExclusive);
    }

    private static int partition(int[] arr, int fromInclusive, int toExclusive) {
        int pivotal = arr[fromInclusive];
        int pos = fromInclusive;
        for (int j = fromInclusive + 1; j < toExclusive; ++j) {
            if (arr[j] <= pivotal) {
                swap(arr, ++pos, j);
            }
        }
        swap(arr, pos, fromInclusive);
        return pos;
    }

    private static void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    private static void initArray(final FastScanner in, int[] arr, int size) {
        for (int i = 0; i < size; i++) {
            arr[i] = in.nextInt();
        }
    }

    private static void fillRepeatedByZeroes(int[] arr, int size) {
        for (int i = size - 1; i > 0; i--) {
            if (arr[i] == arr[i - 1]) {
                arr[i] = 0;
            }
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int aArrSize = in.nextInt();
        int[] aArr = new int[aArrSize];
        initArray(in, aArr, aArrSize);

        int bArrSize = in.nextInt();
        int[] bArr = new int[bArrSize];
        initArray(in, bArr, bArrSize);

        sort(aArr, 0, aArrSize);
        sort(bArr, 0, bArrSize);

        fillRepeatedByZeroes(aArr, aArrSize);
        fillRepeatedByZeroes(bArr, bArrSize);

        int aIndex = aArrSize - 1;
        int bIndex = bArrSize - 1;

        do {
            while (aIndex > 0 && aArr[aIndex] == 0) {
                aIndex--;
            }
            while (bIndex > 0 && bArr[bIndex] == 0) {
                bIndex--;
            }
            if (aArr[aIndex--] != bArr[bIndex--]) {
                out.println("NO");
                return;
            }
        } while (aIndex >= 0 && bIndex >= 0);

        if (aIndex > 0 || bIndex > 0) {
            out.println("NO");
        } else {
            out.println("YES");
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
