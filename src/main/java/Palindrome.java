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
public final class Palindrome {
    private Palindrome() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        int size = in.nextInt();
        char[] array = new char[size];
        int[] countChars = new int[26];
        fillArray(array, in);
        StringBuilder leftHalf = new StringBuilder();
        StringBuilder result = new StringBuilder();
        char center = '0';
        for (int i = 0; i < array.length; i++) {
            countChars[array[i] - 'A']++;
        }
        int indexMaxCenterChar = -1;
        int countMaxCenterChar = -1;
        for (int i = 0; i < countChars.length; i++) {
            if (countChars[i] % 2 == 1 && countChars[i] > countMaxCenterChar) {
                countMaxCenterChar = countChars[i];
                indexMaxCenterChar = i;
            }
        }
        if (indexMaxCenterChar != -1) {
            countChars[indexMaxCenterChar]--;
            center = (char) (indexMaxCenterChar + 'A');
        }
        for (int i = 0; i < countChars.length; i++) {
            if (countChars[i] % 2 == 1) {
                countChars[i]--;
            }
        }
        for (int i = 0; i < countChars.length; i++) {
            if (countChars[i] % 2 == 0 && countChars[i] > 0) {
                int half = countChars[i] / 2;
                while (countChars[i] > half) {
                    leftHalf.append((char) (i + 'A'));
                    countChars[i]--;
                }
            }
        }
        result.append(leftHalf);
        if (center != '0') {
            result.append(center);
        }
        result.append(leftHalf.reverse());
        out.println(result);
    }

    private static void fillArray(char[] array, final FastScanner in) throws IOException {
        String input = in.reader.readLine();
        for (int i = 0; i < array.length; i++) {
            array[i] = input.charAt(i);
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}