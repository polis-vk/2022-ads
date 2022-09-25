package company.vk.polis.ads.part1.tedbear;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BracketSequence {
    private static void solve(final FastScanner in, final PrintWriter out) {
        // Write me
        String brackets = in.next();
        MyDequeue deque = new MyDequeue();

        for (char ch: brackets.toCharArray()) {
            if (ch == '{' || ch == '[' || ch == '(') {
                deque.push_front(ch);
            } else {
                if (deque.size() != 0
                        && ((deque.front() == '{' && ch == '}')
                        || (deque.front() == '[' && ch == ']')
                        || (deque.front() == '(' && ch == ')'))) {
                    deque.removeFirst();
                } else {
                    out.write("no");
                    return;
                }
            }
        }

        if (deque.size() == 0) {
            out.write("yes");
        } else {
            out.write("no");
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

class MyDequeue {
    private int size;
    private final ArrayList<Character> internalArray = new ArrayList<>();

    public void push_front(Character n) {
        internalArray.add(0, n);
        this.size++;

    }

    public String push_back(Character n) {
        internalArray.add(n);
        this.size++;

        return "ok\n";
    }

    public int pop_front() {
        int lastElement = internalArray.get(0);
        internalArray.remove(0);
        this.size--;
        return lastElement;
    }

    public int pop_back() {
        int lastElement = internalArray.get(this.size - 1);
        internalArray.remove(this.size - 1);
        this.size--;
        return lastElement;
    }

    public Character front() {
        return internalArray.get(0);
    }

    public Character back() {
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

    public void removeFirst() {
        this.size--;
        internalArray.remove(0);
    }
}
