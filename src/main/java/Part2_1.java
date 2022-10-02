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
public final class Part2_1 {
    private Part2_1() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int[] newArray = new int[216];
        int n = in.nextInt();
        int centralNumber = in.nextInt();
        newArray[108]++;
        for(int i = 0; i < n - 1; i++) {
            int nextNumber = in.nextInt();
            newArray[getNewIndex(nextNumber, centralNumber)]++;
        }
        for(int i = 0; i < 216; i++) {
            if(newArray[i] != 0) {
                for(int j = 0; j < newArray[i]; j++) {
                    out.print(getNumber(i, centralNumber) + " ");
                }

            }

        }

    }
    private static int getNewIndex(int number, int centralNumber) {
        return 108 + number - centralNumber;
    }
    private static int getNumber(int index, int centralNumber) {
        return index + centralNumber - 108;
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