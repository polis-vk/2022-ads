import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Comparator;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Task3 {
    private Task3() {
        // Should not be instantiated
    }

    public static void sort(int[][] array, Comparator<int[]> comparator) {
        int[][] temp = new int[array.length][2];

        for (int i = 0; i < array.length; i++) {
            temp[i] = array[i];
        }
        mergeSort(array, 0, array.length - 1, comparator, temp);
    }

    public static void mergeSort(int[][] array, int fromInclusive, int toExclusive,
                                Comparator<int[]> comparator, int[][] temp) {
        if (fromInclusive == toExclusive) {
            return;
        }

        int mid = fromInclusive + ((toExclusive - fromInclusive) >> 1);

        mergeSort(array, fromInclusive, mid, comparator, temp);
        mergeSort(array, mid + 1, toExclusive, comparator, temp);
        merge(array, fromInclusive, mid, toExclusive, comparator, temp);
    }

    private static void merge(int[][] array, int fromInclusive, int mid, int toExclusive,
                              Comparator<int[]> comparator, int[][] temp) {
        int i = fromInclusive;
        int j = fromInclusive;
        int q = mid + 1;
        
        while (i <= mid && q <= toExclusive) {
            if (comparator.compare(temp[i], temp[q]) < 0) {
                array[j] = temp[i];
                i++;
            } else {
                array[j] = temp[q];
                q++;
            }
            j++;
        }

        while (i <= mid) {
            array[j] = temp[i];
            j++;
            i++;
        }

        while (q <= toExclusive) {
            array[j] = temp[q];
            j++;
            q++;
        }

        for (int k = fromInclusive; k <= toExclusive; k++) {
            temp[k] = array[k];
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {

        int N = in.nextInt();
        int[][] students = new int[N][2];

        for (int i = 0; i < N; i++) {
            students[i][0] = in.nextInt();
            students[i][1] = in.nextInt();
        }

        sort(students, (o1, o2) -> o1[1] == o2[1] ? o1[0] - o2[0] : o2[1] - o1[1]);

        for (int i = 0; i < N; i++) {
            out.println(students[i][0] + " " + students[i][1]);
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
