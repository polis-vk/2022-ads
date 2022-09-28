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
public final class Part2Task5 {
    private Part2Task5() {
        // Should not be instantiated
    }

    private static final int LATTIN_LETTERS_COUNT = 26;

    private static int getLetterNumber(char ch) {
        return ch - 'A';
    }

    private static char getLetter(int index) {
        return (char) (index + 'A');
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int[] letters = new int[LATTIN_LETTERS_COUNT];
        int n = in.nextInt();
        String str = in.next();
        for (char ch : str.toCharArray()) {
            letters[getLetterNumber(ch)]++;
        }
        int firstOddIndex = -1;
        StringBuilder leftPart = new StringBuilder();
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] % 2 != 0 && firstOddIndex == -1) {
                firstOddIndex = i;
            }
            for (int j = 0; j < letters[i] / 2; j++) {
                leftPart.append(getLetter(i));
            }
        }
        if (firstOddIndex == -1) {
            out.println(leftPart.toString() + leftPart.reverse().toString());
        } else {
            out.println(leftPart.toString() + getLetter(firstOddIndex) + leftPart.reverse().toString());
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
