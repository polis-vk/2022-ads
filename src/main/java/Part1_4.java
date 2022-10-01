import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Part1_4 {
    private Part1_4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String stringOfParentheses = in.next();
        LinkedList<Character> parentheses = new LinkedList<>();
        for (char parenthesis: stringOfParentheses.toCharArray()
             ) {
            parentheses.addLast(parenthesis);
        }
        int i = 0;
        boolean isWrong = false;
        while(!parentheses.isEmpty() && (isWrong == false)) {
            switch (parentheses.get(i)) {
                case '(':
                case '[':
                case '{':
                    if(i + 1 < parentheses.size()) {
                        i++;
                    }
                    else {
                        isWrong = true;
                    }
                    break;
                case ')':
                    if(i - 1 < 0) {
                        isWrong = true;
                    }
                    else {
                        if(parentheses.get(i - 1) != '(') {
                            isWrong = true;
                        }
                        else {
                            i--;
                            parentheses.remove(i);
                            parentheses.remove(i);
                            if(i - 1 >= 0){
                                i--;
                            }
                        }
                    }
                    break;
                case ']':
                    if(i - 1 < 0) {
                        isWrong = true;
                    }
                    else {
                        if(parentheses.get(i - 1) != '[') {
                            isWrong = true;
                        }
                        else {
                            i--;
                            parentheses.remove(i);
                            parentheses.remove(i);
                            if(i - 1 >= 0){
                                i--;
                            }
                        }
                    }
                    break;
                case '}':
                    if(i - 1 < 0) {
                        isWrong = true;
                    }
                    else {
                        if(parentheses.get(i - 1) != '{') {
                            isWrong = true;
                        }
                        else {
                            i--;
                            parentheses.remove(i);
                            parentheses.remove(i);
                            if(i - 1 >= 0){
                                i--;
                            }
                        }
                    }
                    break;
            }
        }
        if(isWrong){
            out.println("no");
        }
        else {
            out.println("yes");
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
