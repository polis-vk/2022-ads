import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class PolishNotation {

    private PolishNotation() {
        // Should not be instantiated
    }

    private static void solve(final BufferedReader bReader, final PrintWriter out) {
        String inputStr;
        try {
            inputStr = bReader.readLine().trim();
        } catch (IOException e) {
            return;
        }

        Deque<Integer> deque = new ArrayDeque<>();
        String[] inputs = inputStr.split(" ");

        for (String input : inputs) {
            if (input.equals("+") || input.equals("*") || input.equals("-")) {
                Integer b = deque.pollLast();
                Integer a = deque.pollLast();
                Integer c = null;

                switch(input) {
                    case "+":
                        c = a + b;
                        break;
                    case "-":
                        c = a - b;
                        break;
                    case "*":
                        c = a * b;
                        break;
                }

                deque.addLast(c);
            } else {
                deque.addLast(Integer.parseInt(input));
            }
        }

        out.println(deque.pollLast());
    }

    public static void main(final String[] arg) {
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(new BufferedReader(new InputStreamReader(System.in)), out);
        }
    }
}
