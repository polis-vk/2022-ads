import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Task5 {
    private Task5() {
        // Should not be instantiated
    }

    public static StringBuilder createPart(int[] countLetters, AtomicInteger oddIdx) {
        StringBuilder part = new StringBuilder();

        for (int i = 0; i < countLetters.length; i++) {

            for (int j = 0; j < countLetters[i] / 2; j++) {
                char letter = (char) (i + 'A');
                part.append(letter);
            }

            if (oddIdx.get() == -1 && countLetters[i] % 2 != 0) {
                oddIdx.set(i);
            }
        }

        return part;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int N = in.nextInt();
        String input = in.next();

        int[] countLetters = new int[26];

        for (int i = 0; i < input.length(); i++) {
            int idxLetter = input.charAt(i) - 'A';
            countLetters[idxLetter]++;
        }

        AtomicInteger oddIdx = new AtomicInteger(-1);
        StringBuilder part = createPart(countLetters, oddIdx);

        String output;

        if (oddIdx.get() != -1) {
            char letter = (char) (oddIdx.get() + 'A');
            output = part.toString() + letter + part.reverse().toString();
        } else {
            output = part.toString() + part.reverse().toString();
        }

        out.println(output);
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
