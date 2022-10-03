import java.io.*;
import java.util.StringTokenizer;

public final class Task5 {
    private Task5() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int alphabetLength = 26;
        int N = in.nextInt();
        int[] charCounts = new int[alphabetLength];
        String str = in.next();
        for (char ch : str.toCharArray()) {
            charCounts[ch - 'A']++;
        }
        StringBuilder ans = new StringBuilder();
        char midChar = ' ';
        for (int i = 0; i < alphabetLength; i++) {
            char current = (char) (i + 'A');
            if (charCounts[i] % 2 == 1 && Character.isSpaceChar(midChar)) {
                midChar = current;
            }
            ans.append(String.valueOf(current).repeat(Math.max(0, charCounts[i] / 2)));
        }
        StringBuilder halfOfAns = new StringBuilder(ans);
        if (!Character.isSpaceChar(midChar)) {
            ans.append(midChar);
        }
        ans.append(halfOfAns.reverse());
        out.println(ans);
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
