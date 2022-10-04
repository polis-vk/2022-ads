import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class TaskD {
    private TaskD() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Set<Integer> firstSet = new HashSet<>();
        Set<Integer> secondSet = new HashSet<>();
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            firstSet.add(in.nextInt());
        }
        n = in.nextInt();
        for (int i = 0; i < n; i++) {
            secondSet.add(in.nextInt());
        }

        int[] firstArray = firstSet.stream()
                .mapToInt(Integer::intValue)
                .toArray();
        int[] secondArray = secondSet.stream()
                .mapToInt(Integer::intValue)
                .toArray();

        quickSort(firstArray, 0, firstArray.length);
        quickSort(secondArray, 0, secondArray.length);

        if (Arrays.equals(firstArray, secondArray)) {
            out.println("YES");
        } else {
            out.println("NO");
        }
    }

    private static void quickSort(int[] array, int fromInclusive, int toExclusive) {
        if (fromInclusive >= toExclusive - 1) {
            return;
        }
        int i = randomPartition(array, fromInclusive, toExclusive);
        quickSort(array, fromInclusive, i);
        quickSort(array, i + 1, toExclusive);
    }

    private static int randomPartition(int[] array, int fromInclusive, int toExclusive) {
        int i = ThreadLocalRandom.current().nextInt(toExclusive - fromInclusive) + fromInclusive;
        swap(array, fromInclusive, i);
        return partition(array, fromInclusive, toExclusive);
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
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
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
