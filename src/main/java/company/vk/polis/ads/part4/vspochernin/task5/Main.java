package company.vk.polis.ads.part4.vspochernin.task5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * https://www.eolymp.com/ru/submissions/11759890
 *
 * @author Dmitry Schitinin
 */
public final class Main {
    private Main() {
        // Should not be instantiated
    }

    private static int[] bufferedArray;

    private static int countInv(int[] a, int from, int to) {
        if (to - from <= 1) { // Осталось не более одного элемента.
            return 0;
        }

        int mid = from + ((to - from) >> 1);

        return countInv(a, from, mid) + countInv(a, mid, to) + countSplitInv(a, from, mid, to);
    }

    private static int countSplitInv(int[] a, int from, int mid, int to) {
        int result = 0;
        int i = from;
        int j = mid;

        while (i < mid && j < to) {
            if (a[i] < a[j]) {
                bufferedArray[i + j - from - mid] = a[i];
                i++;
            } else {
                bufferedArray[i + j - from - mid] = a[j];
                j++;
                result += mid - i;
            }
        }
        while (i < mid) {
            bufferedArray[i + j - from - mid] = a[i];
            i++;
        }
        while (j < to) {
            bufferedArray[i + j - from - mid] = a[j];
            j++;
        }

        for (i = from; i < to; i++) {
            a[i] = bufferedArray[i - from];
        }

        return result;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] nums = new int[n];
        bufferedArray = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }

        out.println(countInv(nums, 0, n));
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
