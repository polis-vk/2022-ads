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
public final class TaskE {

    private static final int ALPHABET_LENGTH = 26;

    private TaskE() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        in.nextInt();
        String letters = in.next();
        System.out.println(makePalindrome(letters));
    }

    public static String makePalindrome(String letters) {
        String mid = "";
        int[] letterCount = new int[ALPHABET_LENGTH];
        for (char c : letters.toCharArray()) {
            letterCount[c - 'A']++;
        }
        StringBuilder leftHalf = new StringBuilder();
        for (int i = 0; i < ALPHABET_LENGTH; i++) {
            if (letterCount[i] >= 2) {
                leftHalf.append(String.valueOf((char) (i + 'A')).repeat(Math.max(0, letterCount[i] / 2)));
            }

            if (letterCount[i] % 2 == 1 && mid.isEmpty()) {
                mid = String.valueOf((char) (i + 'A'));
            }
        }
        StringBuilder rightHalf = new StringBuilder(leftHalf).reverse();
        return leftHalf.append(mid).append(rightHalf).toString();
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
