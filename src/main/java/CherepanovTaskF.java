import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CherepanovTaskF {
    private void SolveTemplate() {
        // Should not be instantiated
    }

    static final char MIN = 'A';

    static final char MAX = 'Z';

    static final char RANGE = (MAX - MIN) + 1;

    private static void solve(final FastScanner in, final PrintWriter out) {
        char[] letters = new char[in.nextInt()];
        String input = in.next();

        for (int i = 0; i < letters.length; i++) {
            letters[i] = input.charAt(i);
        }

        int[] count = countSort(letters);
        printPalindrome(count, out);
    }

    public static int[] countSort(char[] letters) {
        int[] count = new int[RANGE];
        for (char letter : letters) {
            count[letter - MIN]++;
        }
        return count;
    }

    public static void printPalindrome(int[] count, final PrintWriter out) {
        for (int i = 0; i < RANGE; i++) {
            for (int j = 0; j < count[i] / 2; j++) {
                out.print((char) (i + MIN));
            }
        }
        for (int i = 0; i < RANGE; i++) {
            if (count[i] % 2 == 1) {
                out.print((char) (i + MIN));
                break;
            }
        }
        for (int i = RANGE - 1; i >= 0; i--) {
            for (int j = 0; j < count[i] / 2; j++) {
                out.print((char) (i + MIN));
            }
        }
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