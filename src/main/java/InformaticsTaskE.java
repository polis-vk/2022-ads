import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class InformaticsTaskE {
    private InformaticsTaskE() {
        // Should not be instantiated
    }

    private static final int ALPHABET_SIZE = 26;

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        int count = in.nextInt();
        String input = in.reader.readLine();

        int[] alphabet = new int[ALPHABET_SIZE];
        for (int i = 0; i < count; i++) {
            int currLetter = input.charAt(i);
            alphabet[currLetter - 'A']++;
        } // T(N) = O(N)

        StringBuilder sb = new StringBuilder();
        String firstOddChar = "";

        for (int i = 0; i < ALPHABET_SIZE; i++) {
            int currLetterQuantity = alphabet[i];
            sb.append(String.valueOf((char) ('A' + i)).repeat(Math.max(0, currLetterQuantity / 2)));
            if (currLetterQuantity % 2 != 0 && firstOddChar.equals("")) {
                firstOddChar = String.valueOf((char) ('A' + i));
            }
        }  // T(N) = O(N)

        out.println(sb.toString() + firstOddChar + sb.reverse());
    }  // T(N) = O(N) + O(N) = O(N)

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

    public static void main(final String[] arg) throws IOException {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }

    }
}
