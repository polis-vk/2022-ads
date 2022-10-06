import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Palindrome {

    private static final byte AMOUNT_SIZE = 26;

    private static char getCharByIndex(int index) {
        return (char) (index + 'A');
    }

    private static void appendChars(StringBuilder stringBuilder, int countChar, char charToInsert) {
        while (countChar > 0) {
            stringBuilder.append(charToInsert);
            countChar--;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int size = in.nextInt();
        int[] countChar = new int[AMOUNT_SIZE];
        char[] chars = in.next().toCharArray();
        for (char currentChar : chars) {
            countChar[currentChar - 'A']++;
        }

        char midChar = '[';
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < countChar.length; i++) {
            if (countChar[i] == 0) {
                continue;
            }

            if (countChar[i] % 2 == 1) {
                if (countChar[i] - 1 != 0 && (countChar[i] - 1) % 2 == 0) {
                    appendChars(stringBuilder, (countChar[i] - 1) / 2, getCharByIndex(i));
                }
                if (midChar > getCharByIndex(i)) {
                    midChar = getCharByIndex(i);
                }
            } else {
                appendChars(stringBuilder, countChar[i] / 2, getCharByIndex(i));
            }
        }

        out.println((midChar != '[') ? stringBuilder.toString() + midChar + stringBuilder.reverse()
                : stringBuilder.toString() + stringBuilder.reverse());
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