package company.vk.polis.ads.part4.GenryEden;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class EOlymp1087 {
    private EOlymp1087() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String inp = in.next();
        out.println(getAns(inp));
    }

    public static String getAns(String inp){
        return getAns(inp, new HashMap<>());
    }
    private static String getAns(String inp, Map<String, String> cache){
        if (cache.containsKey(inp)) {
            return cache.get(inp);
        }

        if (inp.length() == 0) {
            return writeToHashAndReturn(inp, "", cache);
        }

        if (inp.length() == 1) {
            char c = inp.charAt(0);
            switch (c){
                case '{':
                case '}':
                    return writeToHashAndReturn(inp, "{}", cache);
                case '(':
                case ')':
                    return writeToHashAndReturn(inp, "()", cache);
                case '[':
                case ']':
                    return writeToHashAndReturn(inp, "[]", cache);
            }
        }

        if (inp.charAt(0) == '(' && inp.charAt(inp.length() - 1) == ')') {
            return writeToHashAndReturn(inp,
                    '(' + getAns(inp.substring(1, inp.length()-1), cache) + ')',
                    cache);
        }

        if (inp.charAt(0) == '[' && inp.charAt(inp.length() - 1) == ']') {
            return writeToHashAndReturn(inp,
                    '[' + getAns(inp.substring(1, inp.length()-1), cache) + ']',
                    cache);
        }

        if (inp.charAt(0) == '{' && inp.charAt(inp.length() - 1) == '}') {
            return writeToHashAndReturn(inp,
                    '{' + getAns(inp.substring(1, inp.length()-1), cache) + '}',
                    cache);
        }

        String ans = null;
        for (int i = 1; i < inp.length(); i++){
            String leftSubString = inp.substring(0, i);
            String rightSubString = inp.substring(i);
            String res = getAns(leftSubString, cache) + getAns(rightSubString, cache);
            if (ans == null || res.length() < ans.length()){
                ans = res;
            }
        }
        return writeToHashAndReturn(inp, ans, cache);
    }

    private static String writeToHashAndReturn(String inp, String res, Map<String, String> cache){
        cache.put(inp, res);
        return res;
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
