import java.io.*;
import java.util.StringTokenizer;

public class Subsequence {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int sizeMasOne = in.nextInt();
        int[] masOne = new int[sizeMasOne];

        for (int i = 0; i < sizeMasOne; i++) {
            masOne[i] = in.nextInt();
        }

        int sizeMasTwo = in.nextInt();
        int[] masTwo = new int[sizeMasTwo];

        for (int i = 0; i < sizeMasTwo; i++) {
            masTwo[i] = in.nextInt();
        }

        int[][] tempMas = new int[sizeMasOne + 1][sizeMasTwo + 1];

        for (int i = 1; i <= sizeMasOne; i++) {
            for (int j = 1; j <= sizeMasTwo; j++) {
                if (masOne[i - 1] == masTwo[j - 1]) {
                    tempMas[i][j] = tempMas[i - 1][j - 1] + 1;
                } else {
                    tempMas[i][j] = Math.max(tempMas[i - 1][j], tempMas[i][j - 1]);
                }
            }
        }

        out.println(tempMas[sizeMasOne][sizeMasTwo]);
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