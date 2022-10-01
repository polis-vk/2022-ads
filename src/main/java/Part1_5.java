

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Part1_5 {
    private Part1_5() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Scanner inin = new Scanner(System.in);
        LinkedList<Integer> numbers = new LinkedList<>();
        int firstNumber, secondNumber;
        String expression = inin.nextLine();
        for (String symbol: expression.split(" ")
             ) {
            switch (symbol) {
                case "+":
                    secondNumber = numbers.pollLast();
                    firstNumber = numbers.pollLast();
                    numbers.addLast(firstNumber + secondNumber);
                    break;
                case "-":
                    secondNumber = numbers.pollLast();
                    firstNumber = numbers.pollLast();
                    numbers.addLast(firstNumber - secondNumber);
                    break;
                case "*":
                    secondNumber = numbers.pollLast();
                    firstNumber = numbers.pollLast();
                    numbers.addLast(firstNumber * secondNumber);
                    break;
                default:
                    numbers.addLast(Integer.parseInt(symbol));
                    break;
            }
        }
        out.println(numbers.get(0));
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

