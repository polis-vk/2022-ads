import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class TaskE {
    private TaskE() {
        // Should not be instantiated
    }

    private static Integer tryParse(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static int compute(int num1, int num2, String op) {
        switch (op){
            case "+":
                return num1 + num2;
            case "-":
                return num2 - num1;
            case "*":
                return num1 * num2;
        }
        return -1;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Deque<Integer> deque = new ArrayDeque<>();

        do{
            String ch = in.next();
            Integer num = tryParse(ch);
            if(num != null) {
                deque.push(num);
            }
            else if (ch.equals("+") || ch.equals("-") || ch.equals("*")){
                int res = compute(deque.pop(), deque.pop(), ch);
                deque.push(res);
            }
        } while(in.hasNext());
        out.println(deque.pop());

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

        boolean hasNext() {
            return tokenizer.hasMoreTokens();
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
