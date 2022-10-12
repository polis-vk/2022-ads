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
public final class InversionCount {
    private InversionCount() {
        // Should not be instantiated
    }

    //Решение: https://www.eolymp.com/ru/submissions/11765757
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = in.nextInt();
        }

        int[] tempNumbers = new int[n];
        out.println(countInv(numbers, 0, n, tempNumbers));
    }

    private static int countInv(int[] numbers, int fromInclusive, int toExclusive, int[] tempNumbers) {
        if (toExclusive - fromInclusive <= 1) {
            return 0;
        }
        int mid = fromInclusive + (toExclusive - fromInclusive) / 2;
        return countInv(numbers, fromInclusive, mid, tempNumbers) +
                countInv(numbers, mid, toExclusive, tempNumbers) +
                merge(numbers, fromInclusive, mid, toExclusive, tempNumbers);
    }

    private static int merge(int[] numbers, int fromInclusive, int mid, int toExclusive, int[] tempNumbers) {
        int i = fromInclusive;
        int j = mid;
        int index = fromInclusive;
        int count = 0;
        while (i < mid && j < toExclusive) {
            if (numbers[i] <= numbers[j]) {
                tempNumbers[index++] = numbers[i++];
            } else {
                tempNumbers[index++] = numbers[j++];
                count += (mid - i);
            }
        }
        while (i < mid) {
            tempNumbers[index++] = numbers[i++];
        }
        while (j < toExclusive) {
            tempNumbers[index++] = numbers[j++];
        }

        if (index >= fromInclusive)
            System.arraycopy(tempNumbers, fromInclusive, numbers, fromInclusive, index - fromInclusive);
        return count;
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

