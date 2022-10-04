import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TaskE {
    private TaskE() {
        // Should not be instantiated
    }

    private static final int NUMBER_A = 65;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        char[] letters = in.next().toCharArray();
        int[] numberOfLetters = new int[26];

        for (int i = 0; i < n; i++) {
            numberOfLetters[(int) letters[i] - NUMBER_A]++;
        }

        StringBuilder builderPalindrome = new StringBuilder();
        for (int i = 0; i < numberOfLetters.length; i++) {
            builderPalindrome.append(String.valueOf((char) (i + NUMBER_A)).repeat(Math.max(0, numberOfLetters[i] / 2)));
        }

        for (int i = 0; i < numberOfLetters.length; i++) {
            if (numberOfLetters[i] % 2 == 1) {
                builderPalindrome.append((char) (i + NUMBER_A));
                break;
            }
        }

        for (int i = numberOfLetters.length - 1; i >= 0; i--) {
            builderPalindrome.append(String.valueOf((char) (i + NUMBER_A)).repeat(Math.max(0, numberOfLetters[i] / 2)));
        }

        out.print(builderPalindrome);
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
