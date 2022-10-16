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
public final class EOlymp15 {
    private EOlymp15() {
        // Should not be instantiated
    }
    // submission url: https://www.eolymp.com/ru/submissions/11778389
    private static void solve(final FastScanner in, final PrintWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();
        int[][] inp = new int[m][n];

        for (int mCounter = 0; mCounter < m; mCounter++){
            for (int nCounter = 0; nCounter < n; nCounter++){
                inp[mCounter][nCounter] = in.nextInt();
            }
        }

        int[][] dpArray = new int[m][n];

        for (int mDpCounter = m - 1; mDpCounter >= 0; mDpCounter--){
            for (int nDpCounter = 0; nDpCounter < n; nDpCounter++) {
                if (mDpCounter != m - 1) {
                    dpArray[mDpCounter][nDpCounter] = dpArray[mDpCounter + 1][nDpCounter] + inp[mDpCounter][nDpCounter];
                }

                if (nDpCounter != 0){
                    dpArray[mDpCounter][nDpCounter] = Math.max(dpArray[mDpCounter][nDpCounter - 1] + inp[mDpCounter][nDpCounter],
                            dpArray[mDpCounter][nDpCounter]);
                }
            }
        }

        StringBuilder ans = new StringBuilder();

        int nBackDp = n - 1;
        int mBackDp = 0;
        while (mBackDp < m - 1 || nBackDp > 0) {
            int mStepValue = -1;
            char toAdd = ' ';
            if (mBackDp < m - 1) {
                mStepValue = dpArray[mBackDp + 1][nBackDp];
                toAdd = 'F';
            }

            if (nBackDp > 0){
                if (dpArray[mBackDp][nBackDp - 1] >= mStepValue){
                    toAdd = 'R';
                }
            }

            if (toAdd == 'R') {
                nBackDp--;
            } else if (toAdd == 'F') {
                mBackDp++;
            } else {
                throw new IllegalStateException();
            }
            ans.append(toAdd);
        }
        out.println(ans.reverse());
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
