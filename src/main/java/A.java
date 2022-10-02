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
public final class A {
    private A() {
        // Should not be instantiated
    }

    static int minNum(int[] arr) {
        int min = Integer.MAX_VALUE;
        for (int num : arr) {
            if (num < min) {
                min = num;
            }
        }
        return min;
    }

    static int maxNum(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    static void CountSort(int[] arr, int min, int max) {
        int minmaxDiff = max - min;

        int[] countArr = new int[minmaxDiff + 1];
        for (int num : arr) {
            countArr[num - min]++;
        }

        for (int i = 0, j = 0; i < countArr.length; i++) {
            while (countArr[i] > 0) {
                arr[j] = i + min;
                countArr[i]--;
                j++;
            }
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int numAmount = in.nextInt();
        int[] numsIn = new int[numAmount];

        for (int i = 0; i < numAmount; i++) {
            numsIn[i] = in.nextInt();
        }
        int numMin = minNum(numsIn);
        int numMax = maxNum(numsIn);

        CountSort(numsIn, numMin, numMax);

        for (int i = 0; i < numsIn.length; i++) {
            out.print(numsIn[i] + " ");
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
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
}
