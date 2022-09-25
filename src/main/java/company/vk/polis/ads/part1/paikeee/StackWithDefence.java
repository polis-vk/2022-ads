package company.vk.polis.ads.part1.paikeee;

import java.util.ArrayList;
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
public final class StackWithDefence {
    private StackWithDefence() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        Stack myStack = new Stack();
        String command = in.reader.readLine();
        while (!command.equals("exit")) {
            switch (command) {
                case "size" -> {
                    out.println(myStack.size());
                }
                case "clear" -> {
                    myStack.clear();
                    out.println("ok");
                }
                case "pop" -> {
                    if (myStack.size() != 0) {
                        out.println(myStack.pop());
                    } else {
                        out.println("error");
                    }
                }
                case "back" -> {
                    if (myStack.size() != 0) {
                        out.println(myStack.back());
                    } else {
                        out.println("error");
                    }
                }
            }
            if (command.startsWith("push")) {
                myStack.push(Integer.parseInt(command.split(" ")[1]));
                out.println("ok");
            }
            command = in.reader.readLine();
        }
        out.println("bye");
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
            try {
                solve(in, out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


class Stack {

    private final ArrayList<Integer> stack;
    private int currentSize;

    public Stack() {
        stack = new ArrayList<>();
        currentSize = 0;
    }

    int size() {
        return currentSize;
    }

    void push(int num) {
        stack.add(num);
        currentSize++;
    }

    int pop() {
        int temp = back();
        stack.remove(currentSize - 1);
        currentSize--;
        return temp;
    }

    int back() {
        return stack.get(currentSize - 1);
    }

    void clear() {
        stack.clear();
        currentSize = 0;
    }
}