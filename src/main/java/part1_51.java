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
public final class part1_51 {
    private part1_51() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        // Write me
        out.println(isCorrect(in.next()) ? "yes" : "no");
    }

    private static boolean isCorrect(String inp){
        Deque<Character> deque = new ArrayDeque<>(); // это хоть и дек, использовать будем только "стэковые" функции
        char c;
        for (int i = 0; i < inp.length(); i++) {
            c = inp.charAt(i);
            if (c == '{' || c == '(' || c == '[') {
                deque.addLast(c); // если это открывающая скобка, то ее просто добавляем в стэк
            } else {
                if (deque.isEmpty()) return false; // если у нас появилась закрывающая скобка, а при этом нет ни одной открытой, которой еще не соответствует закрывающая
                char openBracket = deque.pollLast();
                if (c == '}' && openBracket != '{') return false; // если последняя положенная скобка не соответствует
                if (c == ')' && openBracket != '(') return false;
                if (c == ']' && openBracket != '[') return false;
            }
        }
        return deque.isEmpty(); // если все скобки закрыли, то true, иначе false
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


