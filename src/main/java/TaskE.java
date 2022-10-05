import java.io.*;
import java.util.StringTokenizer;

public class TaskE {

    private static StringBuilder getPalindrom(String str) {
        int[] masAlphabet = new int[26]; // алфавит
        for (int i = 0; i < str.length(); i++) {
            masAlphabet[str.charAt(i) - 'A']++;
        }

        StringBuilder leftPart = new StringBuilder();
        boolean flagLastWithoutPair = true;
        String midSymbol = "";
        for (int i = 0; i < masAlphabet.length; i++) {
            if (masAlphabet[i] >= 2) {
                for (int j = 0; j < masAlphabet[i] / 2; j++) {
                    leftPart.append((char) (i + 'A'));
                }
            }

            if (masAlphabet[i] % 2 != 0 && flagLastWithoutPair) {
                midSymbol += (char) (i + 'A');
                flagLastWithoutPair = false;
            }
        }
        StringBuilder rightPart = new StringBuilder(leftPart).reverse();
        return leftPart.append(midSymbol).append(rightPart);
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        in.next();
        String strInput = in.next();
        System.out.println(getPalindrom(strInput));
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
