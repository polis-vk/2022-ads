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
public final class EOlymp262{
    private  EOlymp262() {
        // Should not be instantiated
    }
    // submission url: https://www.eolymp.com/ru/submissions/11779231
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] stairsCosts = new int[n + 2];
        stairsCosts[0] = 0;
        stairsCosts[n + 1] = 0;
        for (int i = 1; i <= n; i++){
            stairsCosts[i] = in.nextInt();
        }
        int k = in.nextInt();
        int[] dpArr = new int[n+2];
        for (int numOfStair = 1; numOfStair < n + 2; numOfStair++){
            dpArr[numOfStair] = Integer.MIN_VALUE;
            for (int offset = 1; offset <= k; offset++){
                if (numOfStair - offset < 0) {
                    break;
                }
                dpArr[numOfStair] = Math.max(dpArr[numOfStair], dpArr[numOfStair - offset] + stairsCosts[numOfStair]);
            }
        }
        out.println(dpArr[n + 1]);
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
