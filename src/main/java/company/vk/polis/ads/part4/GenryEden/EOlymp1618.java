package company.vk.polis.ads.part4.GenryEden;


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
// submission url: https://www.eolymp.com/ru/submissions/11797550
public final class EOlymp1618 {
    private EOlymp1618() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] firstArr = new int[n];
        for (int i = 0; i < n; i++) {
            firstArr[i] = in.nextInt();
        }
        int m = in.nextInt();
        int[] secondArr = new int[m];
        for (int i = 0; i < m; i++) {
            secondArr[i] = in.nextInt();
        }
        out.println(getAns(firstArr, secondArr));
    }

    private static int getAns(int[] firstArr, int[] secondArr) {
        int[][] cache = new int[firstArr.length][secondArr.length];
        for (int i = 0; i < firstArr.length; i++) {
            for (int j = 0; j < secondArr.length; j++) {
                cache[i][j] = -1;
            }
        }
        return getAns(
                firstArr.length - 1,
                secondArr.length - 1,
                firstArr,
                secondArr,
                cache
        );
    }

    private static int getAns(int firstIndex, int secondIndex, int[] firstArr, int[] secondArr, int[][] cache) {
        if (firstIndex < 0 || secondIndex < 0) {
            return 0;
        }

        if (cache[firstIndex][secondIndex] != -1) {
            return cache[firstIndex][secondIndex];
        }

        if (firstArr[firstIndex] == secondArr[secondIndex]) {
            cache[firstIndex][secondIndex] = getAns(
                    firstIndex - 1,
                    secondIndex - 1,
                    firstArr,
                    secondArr,
                    cache) + 1;
        } else {
            cache[firstIndex][secondIndex] = Math.max(
                    getAns(firstIndex - 1, secondIndex, firstArr, secondArr, cache),
                    getAns(firstIndex, secondIndex - 1, firstArr, secondArr, cache)
            );
        }

        return cache[firstIndex][secondIndex];
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
