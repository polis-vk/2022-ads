//package company.vk.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class OlympResults {
    private OlympResults() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int N = in.nextInt();
        int[][] results = new int[N][2];
        for (int i = 0; i < results.length; i++) {
            results[i][0] = in.nextInt();
            results[i][1] = in.nextInt();
        }
        mergeSort(results, (o1, o2) -> o1[1] == o2[1] ? o1[0] - o2[0] : o2[1] - o1[1]);
        for (int[] result : results) {
            System.out.println(result[0] + " " + result[1]);
        }
    }

    public static void mergeSort(int[][] array, Comparator<int[]> comparator) {
        int[][] tempArray = new int[array.length][2];
        for (int i = 0; i < array.length; i++) {
            tempArray[i] = array[i];
        }
        mergeSort(array, comparator, 0, array.length - 1, tempArray);
    }

    private static void mergeSort(int[][] array, Comparator<int[]> comparator,
                                  int fromInclusive, int toInclusive,
                                  int[][] tempArray) {
        if (toInclusive == fromInclusive)
            return;
        int mid = fromInclusive + (toInclusive - fromInclusive) / 2;
        mergeSort(array, comparator, fromInclusive, mid, tempArray);
        mergeSort(array, comparator, mid + 1, toInclusive, tempArray);
        int i = fromInclusive;
        int t = fromInclusive;
        int j = mid + 1;
        while (i <= mid && j <= toInclusive) {
            if (comparator.compare(tempArray[i], tempArray[j]) < 0) {
                array[t] = tempArray[i++];
            } else {
                array[t] = tempArray[j++];
            }
            t++;
        }

        while (i <= mid) {
            array[t++] = tempArray[i++];
        }

        while (j <= toInclusive) {
            array[t++] = tempArray[j++];
        }

        for (int k = fromInclusive; k <= toInclusive; k++) {
            tempArray[k] = array[k];
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
