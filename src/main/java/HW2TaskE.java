import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class HW2TaskE {

    private HW2TaskE() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int length = in.nextInt();
        int[] letterCount = new int[26];
        char[] letters = in.next().toCharArray();
        for (char letter : letters) {
            letterCount[letter - 'A']++;
        }
        StringBuilder left = new StringBuilder();
        String middle = "";
        for (int i = 0; i < letterCount.length; i++) {
            if (letterCount[i] % 2 == 1 && middle.isEmpty()) {
                middle = String.valueOf((char) ('A' + i));
            }
            if (letterCount[i] >= 2) {
                left.append(String.valueOf((char) ('A' + i)).repeat(letterCount[i] / 2));
            }
        }
        StringBuilder right = new StringBuilder(left).reverse();
        out.println(left.append(middle).append(right));
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
