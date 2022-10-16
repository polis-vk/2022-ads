import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class GreatestCommonSubsequence {
    private GreatestCommonSubsequence() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int[] firstArray;
        int[] secondArray;
        int[][] result;
        int sizeFirstArray;
        int sizeSecondArray;
        sizeFirstArray = in.nextInt();
        firstArray = new int[sizeFirstArray + 1];
        for (int i = 1; i <= sizeFirstArray; i++){
            firstArray[i] = in.nextInt();
        }
        sizeSecondArray = in.nextInt();
        secondArray = new int[sizeSecondArray + 1];
        for (int i = 1; i <= sizeSecondArray; i++){
            secondArray[i] = in.nextInt();
        }
        result = new int[2][sizeSecondArray + 1];
        for (int i = 1; i <= sizeFirstArray; i++){
            for (int j = 1; j <= sizeSecondArray; j++){
                if (firstArray[i] == secondArray[j]){
                    result[i % 2][j] = 1 + result[(i - 1) % 2][j - 1];
                }
                else{
                    result[i % 2][j] = Math.max(result[(i - 1) % 2][j], result[i % 2][j - 1]);
                }
            }
        }
        out.println(result[sizeFirstArray % 2][sizeSecondArray]);
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
