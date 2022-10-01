import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class TaskE {
    private TaskE() {
        // Should not be instantiated
    }

    private static final int CHAR_NUMBER = 26;

    private static void solve(final FastScanner in, final PrintWriter out) {
        // Read data
        int size = in.nextInt();
        String str = in.next();
        // Create frequency map
        int[] alphabet = new int[CHAR_NUMBER];
        for (char ch: str.toCharArray()) {
            alphabet[ch - 'A']++;
        }
        // Build palindrome
        String midPart = "";
        StringBuilder leftPart = new StringBuilder();
        for (int i = 0; i < alphabet.length; i++) {
            char ch = (char) ('A' + i);
            int count = alphabet[i];
            if (midPart.isEmpty() && count % 2 != 0) {
                midPart = String.valueOf(ch);
            }
            leftPart.append(String.valueOf(ch).repeat(count / 2));
        }
        StringBuilder rightPart = new StringBuilder(leftPart).reverse();
        out.print(leftPart + midPart + rightPart);
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
