package company.vk.polis.ads.part4.GenryEden;

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
public final class EOlymp1087Rework{
    private EOlymp1087Rework() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        String inp = in.reader.readLine();
        String[][] cache = new String[inp.length()+1][inp.length()+1];
        out.println(fixParentheses(inp, 0, inp.length(), cache));
    }

    private static String fixParentheses(String inp, int fromInc, int toExc, String[][] cache) {
        if (cache[fromInc][toExc] != null) {
            //pass
        } else if (fromInc == toExc) {
            cache[fromInc][toExc] = "";
        } else if (fromInc == toExc - 1) {
            switch (inp.charAt(fromInc)) {
                case '{':
                case '}':
                    cache[fromInc][toExc] = "{}";
                    break;
                case '[':
                case ']':
                    cache[fromInc][toExc] = "[]";
                    break;
                case '(':
                case ')':
                    cache[fromInc][toExc] = "()";
                    break;
                default:
                    cache[fromInc][toExc] = String.valueOf(inp.charAt(fromInc));
            }
        } else if (isFriendly(inp.charAt(fromInc), inp.charAt(toExc-1))) {
            cache[fromInc][toExc] = inp.charAt(fromInc) + fixParentheses(inp, fromInc+1, toExc-1, cache) + inp.charAt(toExc-1);
        } else {
            String ans = null;
            for (int i = fromInc + 1; i < toExc; i++) {
                String leftPartFix = fixParentheses(inp, fromInc, i, cache);
                String rightPartFix = fixParentheses(inp, i, toExc, cache);
                if (ans == null || ans.length() > leftPartFix.length() + rightPartFix.length()) {
                    ans = leftPartFix + rightPartFix;
                }
            }
            cache[fromInc][toExc] = ans;
        }
        return cache[fromInc][toExc];
    }

    private static boolean isFriendly(char first, char second) {
        return (first == '[' && second == ']') || (first == '{' && second == '}') || (first == '(' && second == ')');
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
            throw new RuntimeException(e);
        }
    }
}
