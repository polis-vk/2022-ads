package company.vk.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//Количество инверсий

public class NumberOfInversions {
    private NumberOfInversions() {
        // Should not be instantiated
    }

    private static int[] buffer;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] array = new int[n];
        buffer = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }
        out.println(countInv(array, 0, n));
    }

    private static int countInv(int[] array, int i, int j) {
        if (j - i <= 1) {
            return 0;
        }
        int mid = (i + j) / 2;
        return countInv(array, i, mid) + countInv(array, mid, j) + countSplitInv(array, i, mid, j);
    }

    private static int countSplitInv(int[] array, int fromInclusive, int mid, int toExclusive) {
        int pointer1 = fromInclusive;
        int pointer2 = mid;
        int countInv = 0;

        if (toExclusive - fromInclusive >= 0) {
            System.arraycopy(array, fromInclusive, buffer, fromInclusive, toExclusive - fromInclusive);
        }

        for (int i = fromInclusive; i < toExclusive; i++) {
            if (pointer1 == mid) {
                array[i] = buffer[pointer2++];
            } else if (pointer2 == toExclusive) {
                array[i] = buffer[pointer1++];
            } else if (buffer[pointer1] < buffer[pointer2]) {
                array[i] = buffer[pointer1++];
            } else {
                array[i] = buffer[pointer2++];
                countInv += mid - pointer1;
            }
        }
        return countInv;
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
