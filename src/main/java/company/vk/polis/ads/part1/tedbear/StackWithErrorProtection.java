package company.vk.polis.ads.part1.tedbear;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class StackWithErrorProtection {
    private int size;
    private final ArrayList<Integer> internalArray = new ArrayList<>();

    public String push(Integer n) {
        internalArray.add(n);
        this.size++;

        return "ok\n";
    }

    public int pop() {
        int lastElement = internalArray.get(this.size - 1);
        internalArray.remove(this.size - 1);
        this.size--;
        return lastElement;
    }

    public Integer back() {
        return internalArray.get(this.size - 1);
    }

    public Integer size() {
        return this.size;
    }

    public String clear() {
        this.size = 0;
        internalArray.clear();

        return "ok\n";
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        // Write me
        String command = in.next();
        StackWithErrorProtection stack = new StackWithErrorProtection();

        while(!command.equals("exit")) {
            switch (command) {
                case "pop" -> {
                    if (stack.size() == 0) {
                        out.write("error\n");
                        break;
                    }
                    out.write(stack.pop() + "\n");
                }
                case "back" -> {
                    if (stack.size() == 0) {
                        out.write("error\n");
                        break;
                    }
                    out.write(stack.back() + "\n");
                }
                case "size" -> {
                    out.write(stack.size() + "\n");
                }
                case "clear" -> {
                    out.write(stack.clear() + "\n");
                }
                case "push" -> {
                    Integer number = in.nextInt();
                    out.write(stack.push(number));
                }
            }
            command = in.next();
        }
        out.write("bye\n");
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
